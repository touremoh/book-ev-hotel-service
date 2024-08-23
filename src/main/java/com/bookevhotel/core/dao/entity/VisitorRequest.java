package com.bookevhotel.core.dao.entity;

import com.bookevhotel.core.dao.BookEVHotelEntity;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Getter
@Setter
@Document(collection = "visitorRequests")
public class VisitorRequest implements BookEVHotelEntity {
	@Id
	private ObjectId id;
	private String searchLocation;
	private Date timestamp;

	@Override
	public ObjectId getId() {
		return id;
	}
}

