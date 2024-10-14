package com.bookevhotel.core.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchKeywordDTO implements BookEVHotelDTO {
    private String id;
    private String key;
    private List<String> values;
}
