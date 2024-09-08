package com.bookevhotel.core.exception;

public class BookEVHotelException extends Exception {
	private String message;
	public BookEVHotelException(String message) {
		super(message);
	}

	public BookEVHotelException(String message, Throwable cause) {
		super(message, cause);
	}
}
