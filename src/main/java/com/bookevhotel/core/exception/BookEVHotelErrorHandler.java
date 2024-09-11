package com.bookevhotel.core.exception;

import com.bookevhotel.core.dto.BookEVHotelErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class BookEVHotelErrorHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value = {BookEVHotelException.class, Exception.class})
	protected ResponseEntity<Object> handleConflict(Exception ex) {
		var response = BookEVHotelErrorResponse.builder()
			.message(ex.getMessage())
			.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
			.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.timestamp(LocalDateTime.now())
			.build();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
}
