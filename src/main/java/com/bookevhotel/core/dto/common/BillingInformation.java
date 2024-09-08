package com.bookevhotel.core.dto.common;

import com.bookevhotel.core.dao.entity.PaymentsInformation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillingInformation {
    private String nameOnCard;
    private String cardNumber;
    private ExpirationDate expirationDate;
    private String cvc;
    private Location billingAddress;
}