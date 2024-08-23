package com.bookevhotel.core.dao.entity;

import com.bookevhotel.core.dao.BookEVHotelEntity;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "analytics")
public class Analytics implements BookEVHotelEntity {
	@Id
	private ObjectId id;
	private ObjectId hotelId;
	private int timesAppearedInSearch;
	private int timesProfileViewed;
	private int timesLinkClicked;

	@Override
	public ObjectId getId() {
		return id;
	}
}
