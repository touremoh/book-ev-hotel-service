package com.bookevhotel.core.dao.entity;

import com.bookevhotel.core.dao.BookEVHotelEntity;
import com.bookevhotel.core.dto.common.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Hotels")
public class Hotel implements BookEVHotelEntity {

    @MongoId
    private ObjectId id;

    @NotNull(message = "Hotel name is mandatory")
    @NotBlank(message = "Invalid input")
    private String hotelName;

    private String hotelDescription;

    @NotNull(message = "The hotel location is mandatory")
    private Location location;

    @NotNull(message = "The hotel website link is mandatory")
    @NotBlank(message = "Invalid input")
    private String websiteLink;


    private List<String> amenities;

    @NotNull(message = "The information about the charging point is mandatory")
    private ChargingInformation chargingInformation;

    private List<Offer> offers;
}
