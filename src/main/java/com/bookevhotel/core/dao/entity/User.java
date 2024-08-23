package com.bookevhotel.core.dao.entity;

import com.bookevhotel.core.dao.BookEVHotelEntity;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "users")
public class User implements BookEVHotelEntity {
	@Id
	private ObjectId id;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private ObjectId hotelId;

	@Override
	public ObjectId getId() {
		return id;
	}
}
