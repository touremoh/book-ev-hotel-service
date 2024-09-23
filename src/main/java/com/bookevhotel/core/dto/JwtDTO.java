package com.bookevhotel.core.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class JwtDTO implements Serializable {
	private String token;
}
