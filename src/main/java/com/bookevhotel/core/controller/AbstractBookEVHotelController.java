package com.bookevhotel.core.controller;

import com.bookevhotel.core.dto.BookEVHotelDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import com.bookevhotel.core.service.BookEVHotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
public class AbstractBookEVHotelController<D extends BookEVHotelDTO> {
	protected final BookEVHotelService<D> service;

	public AbstractBookEVHotelController(BookEVHotelService<D> service) {
		this.service = service;
	}

	@PostMapping("/one")
	public ResponseEntity<D> findOne(@RequestBody D dto) throws BookEVHotelException {
		return ResponseEntity.ok(this.service.findOne(dto));
	}
}
