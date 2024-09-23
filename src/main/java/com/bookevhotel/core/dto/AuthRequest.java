package com.bookevhotel.core.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthRequest implements BookEVHotelDTO {
	private String email;
	private String password;

	@Override
	public String getId() {
		return "";
	}
}
