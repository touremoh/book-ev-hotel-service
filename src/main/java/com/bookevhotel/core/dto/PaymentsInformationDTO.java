package com.bookevhotel.core.dto;

import com.bookevhotel.core.dto.common.Location;
import com.bookevhotel.core.dto.common.PaymentHistory;
import com.bookevhotel.core.dto.common.SubscriptionInformation;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentsInformationDTO implements BookEVHotelDTO {
    private String id;
    private String hotelId;
    private Location billingInformation;
    private SubscriptionInformation subscriptionInformation;
    private List<PaymentHistory> paymentHistory;
}
