package com.bookevhotel.core.controller;

import com.bookevhotel.core.dto.BookEVHotelRequestResponse;
import com.bookevhotel.core.dto.HotelUserDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import com.bookevhotel.core.service.AuthenticationService;
import com.bookevhotel.core.utils.BuildApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

	private final AuthenticationService authenticationService;

	@Autowired
	public AuthenticationController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookEVHotelRequestResponse> authenticate(@RequestBody HotelUserDTO hotelUserDTO) throws BookEVHotelException {
		return BuildApiResponse.from(this.authenticationService.authenticate(hotelUserDTO));
	}

	@PostMapping(path = "/token/validate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookEVHotelRequestResponse> validateToken(@RequestHeader("Authorization") String authorizationHeader) throws BookEVHotelException {
		return BuildApiResponse.from(this.authenticationService.isTokenValid(authorizationHeader));
	}

	@PostMapping(path = "/token/refresh", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookEVHotelRequestResponse> refresh(@RequestHeader("Authorization") String authorizationHeader) throws BookEVHotelException {
		return BuildApiResponse.from(this.authenticationService.refreshToken(authorizationHeader));
	}
}
