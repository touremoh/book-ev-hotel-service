package com.bookevhotel.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
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
