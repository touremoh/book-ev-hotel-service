package com.bookevhotel.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public abstract class AbstractBookEVHotelDTO implements BookEVHotelDTO {
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	protected String languageCode;
}
