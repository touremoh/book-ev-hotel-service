package com.bookevhotel.core.enums;

import lombok.Getter;

@Getter
public enum UserRoleEnum {
	OWNER("owner"),
	MAINTAINER("maintainer");

	UserRoleEnum(String value) {
		this.value = value;
	}
	private final String value;
}
