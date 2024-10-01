package com.bookevhotel.core.controller;

import com.bookevhotel.core.dto.BookEVHotelRequestResponse;
import com.bookevhotel.core.dto.HotelUserDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import com.bookevhotel.core.service.HotelUserService;
import com.bookevhotel.core.utils.BuildApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegistrationController {

	private final HotelUserService hotelUserService;

	public RegistrationController(HotelUserService hotelUserService) {
		this.hotelUserService = hotelUserService;
	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookEVHotelRequestResponse> createRegistration(@RequestBody HotelUserDTO hotelUserDTO) throws BookEVHotelException {
		return BuildApiResponse.from(this.hotelUserService.createOne(hotelUserDTO));
	}
}
