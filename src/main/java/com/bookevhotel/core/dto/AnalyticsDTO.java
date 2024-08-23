package com.bookevhotel.core.dto;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class AnalyticsDTO implements BookEVHotelDTO {
	private String id;
	private String hotelId;
	private int timesAppearedInSearch;
	private int timesProfileViewed;
	private int timesLinkClicked;

	@Override
	public String getId() {
		return id;
	}
}
