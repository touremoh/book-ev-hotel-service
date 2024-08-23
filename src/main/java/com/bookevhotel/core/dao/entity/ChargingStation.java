package com.bookevhotel.core.dao.entity;

import com.bookevhotel.core.dao.BookEVHotelEntity;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;

@Getter
@Setter
@Document(collection = "chargingStations")
public class ChargingStation implements BookEVHotelEntity {
	@Id
	private ObjectId id;
	private ObjectId hotelId;
	private int numberOfConnectors;
	private String maxPower;
	private String availabilityTime;
	private String accessibility;
	private String chargingNetwork;
	private BigDecimal energyCostPerKWh;
	private String hotelPhoneNumber;
	private String roadsideAssistanceNumber;

	@Override
	public ObjectId getId() {
		return id;
	}
}

