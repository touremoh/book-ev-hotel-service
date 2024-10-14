package com.bookevhotel.core.service;

import com.bookevhotel.core.dao.entity.Hotel;
import com.bookevhotel.core.dao.repository.HotelRepositoryImpl;
import com.bookevhotel.core.dto.ExcludedSearchWordDTO;
import com.bookevhotel.core.dto.HotelDTO;
import com.bookevhotel.core.dto.HotelUserDTO;
import com.bookevhotel.core.dto.SearchKeywordDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import com.bookevhotel.core.mapper.lombok.HotelMapper;
import com.bookevhotel.core.mapper.lombok.SearchKeywordMapper;
import com.bookevhotel.core.validation.HotelServiceValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.springframework.util.ObjectUtils.isEmpty;

@Slf4j
@Service
public class HotelService  extends AbstractBookEVHotelService<Hotel, HotelDTO> {
	private final SearchKeywordMapper searchKeywordMapper;

	protected final SearchKeywordService searchKeywordService;
	protected final ExcludedSearchKeywordService excludedSearchKeywordService;
	protected final HotelUserService hotelUserService;

	@Autowired
	public HotelService(HotelRepositoryImpl repository,
						HotelMapper mapper,
						HotelServiceValidator validator,
						SearchKeywordService searchKeywordService,
						ExcludedSearchKeywordService excludedSearchKeywordService, HotelUserService hotelUserService,
						SearchKeywordMapper searchKeywordMapper) {
		super(repository, mapper, validator);
		this.searchKeywordService = searchKeywordService;
		this.excludedSearchKeywordService = excludedSearchKeywordService;
		this.hotelUserService = hotelUserService;
		this.searchKeywordMapper = searchKeywordMapper;
	}

	@Override
	protected void processBeforeCreateOne(HotelDTO hotelDTO) throws BookEVHotelException {
		// Get the information of the person who updated the resource
		var username = SecurityContextHolder.getContext().getAuthentication().getName();

		// Find the user in the db
		var foundUser = this.hotelUserService.findOne(HotelUserDTO.builder().email(username).build());

		// Check if the user was found
		if (Objects.nonNull(foundUser)) {
			hotelDTO.setCreatedBy(foundUser.getId());
			hotelDTO.setLastUpdatedBy(foundUser.getId());
		}
	}

	@Override
	protected void processAfterCreateOne(HotelDTO hotelDTO) throws BookEVHotelException {
		this.updateSearchKeywords(hotelDTO);
	}

	@Override
	protected void processBeforeUpdateOne(HotelDTO hotelDTO) throws BookEVHotelException {
		// Get the information of the person who updated the resource
		var username = SecurityContextHolder.getContext().getAuthentication().getName();

		// Find the user in the db
		var foundUser = this.hotelUserService.findOne(HotelUserDTO.builder().email(username).build());

		// Check if the user was found
		if (Objects.nonNull(foundUser)) {
			hotelDTO.setLastUpdatedBy(foundUser.getId());
		}

		// Find Hotel Info In DB
		var foundHotel = this.findOne(HotelDTO.builder().id(hotelDTO.getId()).build());

		// Update the found data with the found
		if (Objects.nonNull(foundHotel) && foundHotel.getId().equals(hotelDTO.getId())) {
			this.mapper.merge(hotelDTO, foundHotel);
		}
	}

	@Override
	protected void processAfterUpdateOne(HotelDTO hotelDTO) throws BookEVHotelException {
		this.updateSearchKeywords(hotelDTO);
	}

	@SafeVarargs
	protected final List<String> concatLists(List<String>... listOfList) throws BookEVHotelException {
		if (listOfList.length == 0) {
			throw new BookEVHotelException(
				"The list should not be empty",
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR
			);
		}

		// Get the length of the list
		List<String> result = new ArrayList<>();

		// concat without number and without duplicates
		for (List<String> list : listOfList) {
			for (String word : list) {
				if (!result.contains(word) && word.matches("\\b[a-zA-ZÀ-ÖØ-öø-ÿßæœçñ'-]+\\b")) {
					result.add(word);
				}
			}
		}
		return result;
	}

	protected void updateSearchKeywords(HotelDTO hotelDTO) throws BookEVHotelException {
		// Split hotel name
		List<String> wordsFromHotelName = this.splitString(hotelDTO.getHotelName());

		// Split hotel description
		List<String> wordsFromHotelDescription = this.splitString(hotelDTO.getHotelDescription());

		// Split hotel location
		List<String> wordsFromHotelAddress = this.splitString(hotelDTO.getLocation().toString());

		// Find words to exclude
		var exclusionCriteria = ExcludedSearchWordDTO.builder().languageCode(hotelDTO.getLanguageCode()).build();
		var excludedWords = this.excludedSearchKeywordService
			.findAll(exclusionCriteria, this.getDefaultPageSettings())
			.stream()
			.map(ExcludedSearchWordDTO::getKey)
			.toList();

		// Build words without duplicate, without numbers and without excluded word
		List<String> concatedLists = this.concatLists(
			wordsFromHotelName,
			wordsFromHotelDescription,
			wordsFromHotelAddress
		);

		// Exclude irrelevant words
		List<String> words = concatedLists
			.stream()
			.filter(word -> !excludedWords.contains(word))
			.filter(word -> word.length() > 1) // no need to add single letter words
			.toList();

		// Build searchWordDTO without duplicate and numbers
		List<SearchKeywordDTO> newSearchKeywords = words
			.stream()
			.map(keyword ->
				SearchKeywordDTO.builder()
					.key(keyword)
					.languageCode(hotelDTO.getLanguageCode())
					.values(List.of(hotelDTO.getId()))
					.build()
			)
			.toList();

		// Find all search keywords and check if the hotel id was not already added
		var existingSearchKeywords = this.searchKeywordService.findAll(newSearchKeywords, this.getDefaultPageSettings());

		// Filter out new search keyword to prevent duplicate hotels ids
		var finalSearchKeywords = new ArrayList<SearchKeywordDTO>();
		for (SearchKeywordDTO searchKeyword : newSearchKeywords) {
			int kwIndex = this.getKeywordIndex(searchKeyword.getKey(), existingSearchKeywords.getContent());
			if (kwIndex >= 0 && kwIndex < existingSearchKeywords.getContent().size()) {
				var hotelIds = existingSearchKeywords.getContent().get(kwIndex).getValues();
				var hotelId = searchKeyword.getValues().getFirst();
				if (!hotelIds.contains(hotelId)) {
					finalSearchKeywords.add(searchKeyword);
				}
			} else {
				finalSearchKeywords.add(searchKeyword);
			}
		}

		// Add the words in the dictionary using upsert strategy
		if (!finalSearchKeywords.isEmpty()) {
			this.searchKeywordService.createMany(finalSearchKeywords);
		}
	}

	protected List<String> splitString(String input) {
		if (isEmpty(input)) {
			return List.of();
		}
		return Arrays.stream(input.split("[^\\p{L}\\p{M}']+"))
			         .map(String::toLowerCase)
			         .toList();
	}

	protected int getKeywordIndex(String keyword, List<SearchKeywordDTO> keywordList) {
		for (int i = 0; i < keywordList.size(); i++) {
			if (keyword.equals(keywordList.get(i).getKey())) {
				return i;
			}
		}
		return -1;
	}
}
