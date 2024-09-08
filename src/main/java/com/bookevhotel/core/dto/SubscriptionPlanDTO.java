package com.bookevhotel.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionPlanDTO implements BookEVHotelDTO {
    private String id;
    private String subscriptionType;
    private String relatedCountry;
    private String currency;
    private BigDecimal monthlyAmount;
    private BigDecimal yearlyAmount;
}
