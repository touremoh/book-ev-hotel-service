package com.bookevhotel.core.dao.entity;

import com.bookevhotel.core.dao.BookEVHotelEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "OTPCodes")
public class OTPCode implements BookEVHotelEntity {
	@MongoId
	protected ObjectId id;
	private String code;
	private ObjectId userId;
	private String secret;
}
