package com.bookevhotel.core.controller;

import com.bookevhotel.core.dto.BookEVHotelDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import com.bookevhotel.core.mapper.requests.BookEVHotelRequestParamsMapper;
import com.bookevhotel.core.service.BookEVHotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Slf4j
public abstract class AbstractBookEVHotelController<D extends BookEVHotelDTO> {
	protected final BookEVHotelService<D> service;
	protected final BookEVHotelRequestParamsMapper<D> mapper;

	protected AbstractBookEVHotelController(BookEVHotelService<D> service, BookEVHotelRequestParamsMapper<D> mapper) {
		this.service = service;
		this.mapper = mapper;
	}

	@GetMapping(path = "/one", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<D> findOne(@RequestParam Map<String, String> params) throws BookEVHotelException {
		return ResponseEntity.ok(this.service.findOne(this.mapper.map(params)));
	}

	@GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<D>> findAll(@RequestParam Map<String, String> params) throws BookEVHotelException {
		return ResponseEntity.ok(this.service.findAll(this.mapper.map(params), this.mapper.getPage(params)));
	}
}
