package com.bookevhotel.core.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BookEVHotelException extends Exception {
	private HttpStatus status;
	private Integer httpStatusCode;
	private String message;

	public BookEVHotelException(String message) {
		super(message);
	}

	public BookEVHotelException(String message, Integer httpStatusCode, HttpStatus status) {
		super(message);
		this.httpStatusCode = httpStatusCode;
		this.message = message;
	}

	public BookEVHotelException(String message, Throwable cause) {
		super(message, cause);
	}

	public BookEVHotelException(String message, Throwable cause, Integer httpStatusCode, HttpStatus status) {
		super(message, cause);
		this.httpStatusCode = httpStatusCode;
		this.message = message;
	}
}
