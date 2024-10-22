package com.bookevhotel.core.dto;

import com.bookevhotel.core.dto.common.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelDTO extends AbstractBookEVHotelDTO {
    private String id;
    private String hotelName;
    private Location location;
    private String websiteLink;
    private Price minPricePerNight;
    private Price maxPricePerNight;
    private List<String> amenities;
    private List<ChargingInformation> chargingInformation;
    private List<Offer> offers;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime lastUpdatedAt;

    private String createdBy;
    private String lastUpdatedBy;
}