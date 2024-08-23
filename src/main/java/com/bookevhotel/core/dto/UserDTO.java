package com.bookevhotel.core.dto;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class UserDTO implements BookEVHotelDTO {
	private String id;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private ObjectId hotelId;

	@Override
	public String getId() {
		return id;
	}
}
