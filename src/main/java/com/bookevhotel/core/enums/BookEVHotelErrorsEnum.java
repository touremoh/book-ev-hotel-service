package com.bookevhotel.core.enums;

import lombok.Getter;

@Getter
public enum BookEVHotelErrorsEnum {
	RESOURCE_NOT_FOUND_EXCEPTION("400", "Resource not found");

	BookEVHotelErrorsEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}

	private final String code;
	private final String message;
}
