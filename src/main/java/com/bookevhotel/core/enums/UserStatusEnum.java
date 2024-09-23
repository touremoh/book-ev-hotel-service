package com.bookevhotel.core.enums;

import lombok.Getter;

@Getter
public enum UserStatusEnum {
	ACTIVE("active"),
	INACTIVE("inactive"),
	SUSPENDED("suspended"),
	WAITING_FOR_REGISTRATION_VALIDATION("waitingForRegistrationValidation");

	UserStatusEnum(String value) {
		this.value = value;
	}
	private final String value;
}
