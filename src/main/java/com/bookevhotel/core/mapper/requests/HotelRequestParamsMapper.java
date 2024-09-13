package com.bookevhotel.core.mapper.requests;

import com.bookevhotel.core.annotation.RequestParamsMapper;
import com.bookevhotel.core.dto.HotelDTO;
import com.bookevhotel.core.dto.VisitorSearchDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import com.bookevhotel.core.utils.BookEVHotelUtils;

import java.time.LocalDateTime;
import java.util.Map;

@RequestParamsMapper
public class HotelRequestParamsMapper implements BookEVHotelRequestParamsMapper<HotelDTO> {
	@Override
	public HotelDTO map(Map<String, String> params) throws BookEVHotelException {
		BookEVHotelUtils.assertNotNull(params, "Http request params must not be null");

		HotelDTO hotelDTO = new HotelDTO();

		hotelDTO.setId(params.get("id"));

		VisitorSearchDTO visitorSearchDTO = new VisitorSearchDTO();
		visitorSearchDTO.setSearchTerm(params.get("searchTerm"));
		visitorSearchDTO.setRequestTimestamp(LocalDateTime.now());
		visitorSearchDTO.setUserLocation(params.getOrDefault("userLocation", "Unknown"));
		hotelDTO.setVisitorSearch(visitorSearchDTO);
		return hotelDTO;
	}
}
