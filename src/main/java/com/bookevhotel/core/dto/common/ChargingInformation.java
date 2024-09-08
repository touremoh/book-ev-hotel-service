package com.bookevhotel.core.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChargingInformation {
    private int numberOfConnectors;
    private String maxPower;
    private String availabilityTime;
    private String accessibility;
    private String chargingNetwork;
    private EnergyCost energyCost;
    private String messageForCustomer;
}
