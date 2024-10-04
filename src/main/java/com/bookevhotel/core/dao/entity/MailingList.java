package com.bookevhotel.core.dao.entity;

import com.bookevhotel.core.dao.BookEVHotelEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

import org.bson.types.ObjectId;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "MailingList")
public class MailingList implements BookEVHotelEntity {

    @MongoId
    private ObjectId id;
    private String email;
    private String countryOfResidence;
    private LocalDate recordDate;
}
