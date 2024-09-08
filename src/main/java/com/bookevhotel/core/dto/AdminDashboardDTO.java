package com.bookevhotel.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminDashboardDTO implements BookEVHotelDTO {
    private String id;
    private String hotelId;
    private int timesImpressions;
    private int timesProfileViews;
    private int timesCTAClicked;
    private LocalDate recordDate;
}
