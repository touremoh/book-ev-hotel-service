package com.bookevhotel.core.service;

import com.bookevhotel.core.dao.entity.Hotel;
import com.bookevhotel.core.dao.repository.HotelRepositoryImpl;
import com.bookevhotel.core.dto.ExcludedSearchWordDTO;
import com.bookevhotel.core.dto.HotelDTO;
import com.bookevhotel.core.dto.SearchWordDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import com.bookevhotel.core.mapper.lombok.HotelMapper;
import com.bookevhotel.core.validation.HotelServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class HotelService  extends AbstractBookEVHotelService<Hotel, HotelDTO> {

	protected final SearchWordService searchWordService;
	protected final ExcludedSearchWordService excludedSearchWordService;

	@Autowired
	public HotelService(HotelRepositoryImpl repository,
						HotelMapper mapper,
						HotelServiceValidator validator,
						SearchWordService searchWordService,
						ExcludedSearchWordService excludedSearchWordService) {
		super(repository, mapper, validator);
		this.searchWordService = searchWordService;
		this.excludedSearchWordService = excludedSearchWordService;
	}

	@Override
	protected void processAfterCreateOne(HotelDTO hotelDTO) throws BookEVHotelException {
		// Split hotel name
		List<String> wordsFromHotelName = this.splitString(hotelDTO.getHotelName());

		// Split hotel description
		List<String> wordsFromHotelDescription = this.splitString(hotelDTO.getHotelDescription());

		// Split hotel location
		List<String> wordsFromHotelAddress = this.splitString(hotelDTO.getLocation().toString());

		// Find words to exclude
		var page = Pageable.ofSize(1000).withPage(0);
		var exclusionCriteria = ExcludedSearchWordDTO.builder().languageCode(hotelDTO.getLanguageCode()).build();
		var excludedWords = this.excludedSearchWordService
			.findAll(exclusionCriteria, page)
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
		List<SearchWordDTO> searchWords = words
			.stream()
			.map(word ->
				SearchWordDTO.builder()
					.key(word)
					.languageCode(hotelDTO.getLanguageCode())
					.values(List.of(hotelDTO.getId()))
					.build()
			)
			.toList();

		// Add the words in the dictionary using upsert strategy
		this.searchWordService.createMany(searchWords);
	}

	protected List<String> splitString(String input) {
		return Arrays.stream(input.split("[^\\p{L}\\p{M}']+"))
			         .map(String::toLowerCase)
			         .toList();
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
}
