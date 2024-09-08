package com.bookevhotel.core.mapper;

import com.bookevhotel.core.dao.entity.HotelLead;
import com.bookevhotel.core.dto.HotelLeadDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HotelLeadMapper extends BookEVHotelMapper<HotelLead, HotelLeadDTO> {
}
