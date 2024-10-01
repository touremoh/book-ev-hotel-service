package com.bookevhotel.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivationCodeDTO {
	@JsonProperty(value = "code")
	private String code;
}
