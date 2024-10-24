package com.bookevhotel.core.dto;

import com.bookevhotel.core.dto.common.Offer;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelLeadDTO extends AbstractBookEVHotelDTO {
    private String id;
    private String email;
    private String fullName;
    private String hotelId;
    private LocalDateTime recordTimestamps;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Offer requestedOffer;
}
