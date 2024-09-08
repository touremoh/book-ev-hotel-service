package com.bookevhotel.core.controller;

import com.bookevhotel.core.dto.HotelDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import com.bookevhotel.core.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Autowired
	protected HotelService hotelService;

	@GetMapping("/test")
	public HotelDTO test() throws BookEVHotelException {
		var hotel = new HotelDTO();
		hotel.setId("66dc16ddf8135a197e5f6ee7");
		//hotel.setHotelName("Grand Plaza");
		return this.hotelService.findOneDocument(hotel);
	}
}
