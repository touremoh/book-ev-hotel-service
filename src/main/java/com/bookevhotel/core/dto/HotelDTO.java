package com.bookevhotel.core.dto;

import com.bookevhotel.core.dto.common.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelDTO implements BookEVHotelDTO {
    private String id;
    private String hotelName;
    private String hotelDescription;
    private Location location;
    private String websiteLink;
    private List<String> amenities;
    private ChargingInformation chargingInformation;
    private List<Offer> offers;
}
