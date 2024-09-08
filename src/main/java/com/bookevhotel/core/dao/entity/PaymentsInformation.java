package com.bookevhotel.core.dao.entity;

import com.bookevhotel.core.dao.BookEVHotelEntity;
import com.bookevhotel.core.dto.common.BillingInformation;
import com.bookevhotel.core.dto.common.PaymentHistory;
import com.bookevhotel.core.dto.common.SubscriptionInformation;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "PaymentsInformationMapper")
public class PaymentsInformation implements BookEVHotelEntity {

    @MongoId
    private ObjectId id;

    private String hotelId;
    private BillingInformation billingInformation;
    private SubscriptionInformation subscriptionInformation;
    private List<PaymentHistory> paymentHistory;
}
