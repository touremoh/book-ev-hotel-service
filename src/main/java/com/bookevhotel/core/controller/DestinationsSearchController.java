package com.bookevhotel.core.controller;

import com.bookevhotel.core.dto.HotelDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import com.bookevhotel.core.service.DestinationSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/destinations")
public class DestinationsSearchController {

	protected final DestinationSearchService service;

	@Autowired
	public DestinationsSearchController(DestinationSearchService service) {
		this.service = service;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<HotelDTO>> search(@RequestParam Map<String, String> params) throws BookEVHotelException {
		return ResponseEntity.ok(this.service.search(params));
	}
}
