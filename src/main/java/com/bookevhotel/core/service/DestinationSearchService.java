package com.bookevhotel.core.service;

import com.bookevhotel.core.dto.SearchKeywordDTO;
import com.bookevhotel.core.dto.HotelDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import com.bookevhotel.core.mapper.requests.VisitorSearchRequestParamsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;

@Slf4j
@Service
public class DestinationSearchService {
	protected final HotelService hotelService;
	protected final VisitorSearchService visitorSearchService;
	protected final SearchKeywordService searchKeywordService;
	protected final VisitorSearchRequestParamsMapper searchRequestParamsMapper;

	public DestinationSearchService(
		HotelService hotelService,
		VisitorSearchService visitorSearchService,
		SearchKeywordService searchKeywordService,
		VisitorSearchRequestParamsMapper searchRequestParamsMapper) {
		this.hotelService = hotelService;
		this.visitorSearchService = visitorSearchService;
		this.searchKeywordService = searchKeywordService;
		this.searchRequestParamsMapper = searchRequestParamsMapper;
	}

	public Page<HotelDTO> search(Map<String, String> params) throws BookEVHotelException {
		// Map params
		var searchRequest = this.searchRequestParamsMapper.map(params);
		var searchReqPage = this.searchRequestParamsMapper.getPage(params);

		// Save visitor's search
		this.visitorSearchService.createOne(searchRequest);

		// Create word pattern to split search keywords
		Pattern pattern = Pattern.compile("\\s+");

		// Create a list of dictionary keywords from visitor's search request
		List<SearchKeywordDTO> keywords = pattern.splitAsStream(searchRequest.getSearchTerm())
			.map(String::toLowerCase)
			.map(searchTerm -> SearchKeywordDTO.builder().key(searchTerm).build())
			.toList();

		// Search into dictionary
		var searchKeywords = this.searchKeywordService
			.findAll(keywords, searchReqPage)
			.getContent();

		// Check search terms
		if (searchKeywords.isEmpty()) {
			log.warn("No hotel was found for search term {}", searchRequest.getSearchTerm());
			return new PageImpl<>(
				List.of(),
				searchReqPage,
				0
			);
		}

		// The intersection of all sets is the most relevant results
		Set<String> hotelIds = new HashSet<>(searchKeywords.getFirst().getValues());
		for (SearchKeywordDTO keywordDTO : searchKeywords) {
			hotelIds.retainAll(new HashSet<>(keywordDTO.getValues()));
		}

		// Check search terms
		if (hotelIds.isEmpty()) {
			log.warn("No hotel was found for search term {}", searchRequest.getSearchTerm());
			return new PageImpl<>(
				List.of(),
				searchReqPage,
				0
			);
		}

		// Build hotel dto list to perform finAll on hotels collection
		var hotels = hotelIds.stream().map(hotelId -> HotelDTO.builder().id(hotelId).build()).toList();

		// Search elements
		return this.hotelService.findAll(hotels, this.searchRequestParamsMapper.getPage(params));
	}
}
