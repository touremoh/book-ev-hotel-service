package com.bookevhotel.core.enums;

import lombok.Getter;

@Getter
public enum UserStatusEnum {
	ACTIVE("ACTIVE"),
	INACTIVE("INACTIVE"),
	LOCKED("LOCKED"),
	WAITING_FOR_REGISTRATION_VALIDATION("WAITING_FOR_REGISTRATION_VALIDATION");

	UserStatusEnum(String value) {
		this.value = value;
	}
	private final String value;
}
