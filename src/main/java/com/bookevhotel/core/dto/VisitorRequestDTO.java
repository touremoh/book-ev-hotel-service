package com.bookevhotel.core.dto;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.Date;

@Getter
@Setter
public class VisitorRequestDTO implements BookEVHotelDTO {
	private String id;
	private String searchLocation;
	private Date timestamp;

	@Override
	public String getId() {
		return id;
	}
}

