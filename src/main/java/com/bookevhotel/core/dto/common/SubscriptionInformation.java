package com.bookevhotel.core.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionInformation {
    private String currentPlan;
    private String billingCycle;
    private double amount;
    private String currency;
    private String accountStatus;
    private LocalDate subscriptionDate;
    private LocalDate nextBillingDate;
}