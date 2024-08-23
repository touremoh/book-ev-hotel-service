package com.bookevhotel.core.dto;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.math.BigDecimal;

@Getter
@Setter
public class ChargingStationDTO implements BookEVHotelDTO {
	private String id;
	private String hotelId;
	private int numberOfConnectors;
	private String maxPower;
	private String availabilityTime;
	private String accessibility;
	private String chargingNetwork;
	private BigDecimal energyCostPerKWh;
	private String hotelPhoneNumber;
	private String roadsideAssistanceNumber;

	@Override
	public String getId() {
		return id;
	}
}

