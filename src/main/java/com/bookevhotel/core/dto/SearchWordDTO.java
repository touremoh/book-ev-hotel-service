package com.bookevhotel.core.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchWordDTO implements BookEVHotelDTO {
    private String id;
    private String key;
    private String languageCode;
    private List<String> values;
}
