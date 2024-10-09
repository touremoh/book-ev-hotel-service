package com.bookevhotel.core.dao.entity;

import com.bookevhotel.core.dao.BookEVHotelEntity;
import com.bookevhotel.core.dto.common.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Hotels")
public class Hotel implements BookEVHotelEntity {

    @MongoId
    private ObjectId id;
    private String hotelName;
    private String hotelDescription;
    private Location location;
    private String websiteLink;
    private List<String> amenities;
    private ChargingInformation chargingInformation;
    private List<Offer> offers;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime lastUpdatedAt;

    private ObjectId createdBy;
    private ObjectId lastUpdatedBy;
    private String languageCode;
}
