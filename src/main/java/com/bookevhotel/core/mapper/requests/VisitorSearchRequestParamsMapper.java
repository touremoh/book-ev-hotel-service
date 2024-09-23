package com.bookevhotel.core.mapper.requests;

import com.bookevhotel.core.annotation.RequestParamsMapper;
import com.bookevhotel.core.dto.VisitorSearchDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import com.bookevhotel.core.utils.BookEVHotelUtils;

import java.time.LocalDateTime;
import java.util.Map;

@RequestParamsMapper
public class VisitorSearchRequestParamsMapper implements BookEVHotelRequestParamsMapper<VisitorSearchDTO> {
	@Override
	public VisitorSearchDTO map(Map<String, String> params) throws BookEVHotelException {
		BookEVHotelUtils.assertNotNull(params, "Http request params must not be null");
		var visitorSearchDTO = new VisitorSearchDTO();
		visitorSearchDTO.setSearchTerm(params.get("searchTerm"));
		visitorSearchDTO.setRequestTimestamp(LocalDateTime.now());
		visitorSearchDTO.setUserLocation(params.getOrDefault("userLocation", "Unknown"));
		return visitorSearchDTO;
	}
}
