package com.bookevhotel.core.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    private String streetAddress;
    private String extendedAddress;
    private String city;
    private String zipOrPostalCode;
    private String stateOrProvince;
    private String country;
}