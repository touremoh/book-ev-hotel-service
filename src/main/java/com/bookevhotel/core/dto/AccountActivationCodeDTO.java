package com.bookevhotel.core.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountActivationCodeDTO implements BookEVHotelDTO {
    private String id;
    private String activationCode;
    private String userId;
    private LocalDateTime expirationTimestamp;
}
