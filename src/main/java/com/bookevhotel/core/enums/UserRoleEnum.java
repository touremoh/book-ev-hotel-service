package com.bookevhotel.core.enums;

import lombok.Getter;

@Getter
public enum UserRoleEnum {
	OWNER("ROLE_OWNER"),
	MAINTAINER("ROLE_MAINTAINER");

	UserRoleEnum(String value) {
		this.value = value;
	}
	private final String value;
}
