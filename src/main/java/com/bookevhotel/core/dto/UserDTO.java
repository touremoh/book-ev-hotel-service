package com.bookevhotel.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements BookEVHotelDTO  {
    private String id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String hotelId;
    private LocalDateTime recordTimestamp;
}
