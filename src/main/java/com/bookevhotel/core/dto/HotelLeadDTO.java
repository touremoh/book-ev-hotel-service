package com.bookevhotel.core.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelLeadDTO implements BookEVHotelDTO {
    private String id;
    private String email;
    private String fullName;
    private String hotelId;
    private Date recordTimestamps;
}
