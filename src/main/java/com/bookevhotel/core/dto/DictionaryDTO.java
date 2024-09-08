package com.bookevhotel.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryDTO implements BookEVHotelDTO {
    private String id;
    private String key;
    private List<String> values;
}
