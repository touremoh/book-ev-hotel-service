package com.bookevhotel.core.mapper.lombok;

import com.bookevhotel.core.dao.entity.Hotel;
import com.bookevhotel.core.dto.HotelDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HotelMapper extends BookEVHotelMapper<Hotel, HotelDTO> {
}
