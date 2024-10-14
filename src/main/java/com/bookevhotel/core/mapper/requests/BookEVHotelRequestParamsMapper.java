package com.bookevhotel.core.mapper.requests;

import com.bookevhotel.core.dto.BookEVHotelDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.Objects;

@FunctionalInterface
public interface BookEVHotelRequestParamsMapper<D extends BookEVHotelDTO> {
	/**
	 * Given HTTP request params, maps the parameters with DTO
	 * @param params Data to map
	 * @return DTO
	 */
	D map(Map<String, String> params) throws BookEVHotelException;

	/**
	 * Get Page information
	 * @param params request params
	 * @return page
	 */
	default Pageable getPage(Map<String, String> params) {
		int pageNumber = 0;
		int pageSize = 500;
		var page = Pageable.ofSize(pageSize).withPage(pageNumber);

		if (Objects.nonNull(params)) {
			if (params.containsKey("pageNumber")) {
				pageNumber = Integer.parseInt(params.get("pageNumber"));
			}
			if (params.containsKey("pageSize")) {
				pageSize = Integer.parseInt(params.get("pageSize"));
			}
			page = Pageable.ofSize(pageSize).withPage(pageNumber);
		}
		return page;
	}
}
