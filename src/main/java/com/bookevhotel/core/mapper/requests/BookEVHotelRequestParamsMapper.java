package com.bookevhotel.core.mapper.requests;

import com.bookevhotel.core.dto.BookEVHotelDTO;
import com.bookevhotel.core.exception.BookEVHotelException;

import java.util.Map;

@FunctionalInterface
public interface BookEVHotelRequestParamsMapper<D extends BookEVHotelDTO> {
	/**
	 * Given HTTP request params, maps the parameters with DTO
	 * @param params Data to map
	 * @return DTO
	 */
	D map(Map<String, String> params) throws BookEVHotelException;
}
