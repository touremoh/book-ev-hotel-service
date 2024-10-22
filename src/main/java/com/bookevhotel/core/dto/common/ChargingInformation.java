package com.bookevhotel.core.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChargingInformation {
    private int numberOfConnectors;
    private int maxPower;
    private String connectorType;
    private String availabilityTime;
    private String accessibility;
    private String chargingNetwork;
    private EnergyCost energyCost;
    private String messageForCustomer;
    private boolean isDestinationCharger;
    private Location chargerLocation;
}
