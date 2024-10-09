package com.bookevhotel.core.dto;

import com.bookevhotel.core.dto.common.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
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

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime lastUpdatedAt;

    private String createdBy;
    private String lastUpdatedBy;
    private String languageCode;
}