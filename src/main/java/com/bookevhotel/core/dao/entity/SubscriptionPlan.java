package com.bookevhotel.core.dao.entity;

import com.bookevhotel.core.dao.BookEVHotelEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "SubscriptionPlans")
public class SubscriptionPlan implements BookEVHotelEntity {
    @MongoId
    protected ObjectId id;
    private String subscriptionType;
    private String relatedCountry;
    private String currency;
    private BigDecimal monthlyAmount;
    private BigDecimal yearlyAmount;
}
