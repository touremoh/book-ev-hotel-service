package com.bookevhotel.core.controller;

import com.bookevhotel.core.dto.HotelDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	@GetMapping("/test")
	public HotelDTO test() {
		HotelDTO hotelDTO = new HotelDTO();
		return hotelDTO;
	}
}
