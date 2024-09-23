package com.bookevhotel.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelUserDTO implements BookEVHotelDTO  {
    private String id;
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String rawPassword;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String encodedPassword;

    private String firstName;
    private String lastName;
    private String userRole;
    private String userStatus;
    private String hotelId;
    private LocalDateTime recordTimestamp;
}
