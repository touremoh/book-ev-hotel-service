package com.bookevhotel.core.dao.entity;

import com.bookevhotel.core.dao.BookEVHotelEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "HotelsLeads")
public class HotelLead implements BookEVHotelEntity {

    @MongoId
    private ObjectId id;

    private String email;
    private String fullName;
    private ObjectId hotelId;
    private LocalDateTime recordTimestamps;
}
