package com.bookevhotel.core.dao.entity;

import com.bookevhotel.core.dao.BookEVHotelEntity;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Users")
public class HotelUser implements BookEVHotelEntity {

    @MongoId
    protected ObjectId id;
    private String email;

    @Field("password")
    private String encodedPassword;
    private String firstName;
    private String lastName;
    private ObjectId hotelId;
    private String userRole;
    private String userStatus;

    @CreatedDate
    private LocalDateTime recordTimestamp;
}
