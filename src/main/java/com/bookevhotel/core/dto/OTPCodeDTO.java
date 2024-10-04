package com.bookevhotel.core.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OTPCodeDTO implements BookEVHotelDTO {
    private String id;
    private String code;
    private String userId;
    private String secret;
}
