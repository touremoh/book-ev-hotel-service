package com.bookevhotel.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisitorSearchDTO implements BookEVHotelDTO {
    private String id;
    private String searchTerm;
    private String userLocation;
    private LocalDateTime requestTimestamp;
}
