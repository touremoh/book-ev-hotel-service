package com.bookevhotel.core.dto;

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
}
