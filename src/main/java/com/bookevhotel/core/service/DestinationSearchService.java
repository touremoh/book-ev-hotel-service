package com.bookevhotel.core.service;

import com.bookevhotel.core.dto.SearchWordsDTO;
import com.bookevhotel.core.dto.HotelDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import com.bookevhotel.core.mapper.requests.VisitorSearchRequestParamsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;

@Slf4j
@Service
public class DestinationSearchService {
	protected final HotelService hotelService;
	protected final VisitorSearchService visitorSearchService;
	protected final DictionaryService dictionaryService;
	protected final VisitorSearchRequestParamsMapper searchRequestParamsMapper;

	public DestinationSearchService(
		HotelService hotelService,
		VisitorSearchService visitorSearchService,
		DictionaryService dictionaryService,
		VisitorSearchRequestParamsMapper searchRequestParamsMapper) {
		this.hotelService = hotelService;
		this.visitorSearchService = visitorSearchService;
		this.dictionaryService = dictionaryService;
		this.searchRequestParamsMapper = searchRequestParamsMapper;
	}

	public Page<HotelDTO> search(Map<String, String> params) throws BookEVHotelException {
		// Map params
		var searchRequest = this.searchRequestParamsMapper.map(params);

		// Save visitor's search
		this.visitorSearchService.createOne(searchRequest);

		// Create a list of dictionary keywords from visitor's search request
		Pattern pattern = Pattern.compile("\\s+");
		List<SearchWordsDTO> keywords = pattern.splitAsStream(searchRequest.getSearchTerm())
			.map(String::toLowerCase)
			.map(st -> {
				var sw = new SearchWordsDTO();
				sw.setKey(st);
				return sw;
			})
			.toList();

		// Search into dictionary
		var dicoPage = this.dictionaryService.findAll(keywords, this.searchRequestParamsMapper.getPage(params));

		// Merge hotels ids to eliminate duplicates
		var hotelsMap = new HashMap<String, Integer>();
		for (SearchWordsDTO searchWordsDTO : dicoPage.getContent()) {
			for (String hotelId : searchWordsDTO.getValues()) {
				hotelsMap.put(hotelId, hotelsMap.getOrDefault(hotelId, 0) + 1);
			}
		}
		// TODO: take all the hotels ids in hotelMaps that the occurrence is the same as the number of dicoPage.getContent().size()

		var hotels = hotelsMap.keySet().stream().map(hotelId -> {
			var hotel = new HotelDTO();
			hotel.setId(hotelId);
			return hotel;
		}).toList();

		// Search elements
		return this.hotelService.findAll(hotels, this.searchRequestParamsMapper.getPage(params));
	}

}
