package com.bookevhotel.core.dao.entity;

import com.bookevhotel.core.dao.BookEVHotelEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "AdminDashboards")
public class AdminDashboard implements BookEVHotelEntity {
    @MongoId
    private ObjectId id;
    private String hotelId;
    private int timesImpressions;
    private int timesProfileViews;
    private int timesCTAClicked;
    private int countLeads;
    private LocalDate recordDate;
}
