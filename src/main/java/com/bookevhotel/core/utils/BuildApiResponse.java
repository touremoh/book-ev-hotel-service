package com.bookevhotel.core.utils;

import com.bookevhotel.core.dto.BookEVHotelDTO;
import com.bookevhotel.core.dto.BookEVHotelRequestResponse;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@UtilityClass
public class BuildApiResponse {
	public <D extends BookEVHotelDTO> ResponseEntity<BookEVHotelRequestResponse> from(D data) {
		return ResponseEntity.ok(buildResponse(data));
	}

	public <D extends BookEVHotelDTO> ResponseEntity<BookEVHotelRequestResponse> from(Object data) {
		return ResponseEntity.ok(buildResponse(data));
	}

	public <D extends BookEVHotelDTO> ResponseEntity<BookEVHotelRequestResponse> from(Page<D> data) {
		return ResponseEntity.ok(buildResponse(data));
	}

	public ResponseEntity<BookEVHotelRequestResponse> from(Boolean ok) {
		return ResponseEntity.ok(buildResponse(ok));
	}

	private BookEVHotelRequestResponse buildResponse(Object data) {
		var response = new BookEVHotelRequestResponse();
		response.setData(data);
		response.setTimestamp(LocalDateTime.now());
		response.setCode(200);
		return response;
	}
}
