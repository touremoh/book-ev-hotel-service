package com.bookevhotel.core.mapper;

import com.bookevhotel.core.dao.entity.ChargingStation;
import com.bookevhotel.core.dto.ChargingStationDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChargingStationMapper extends BookEVHotelMapper<ChargingStation, ChargingStationDTO> {
}
