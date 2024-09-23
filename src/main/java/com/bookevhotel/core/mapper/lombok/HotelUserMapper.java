package com.bookevhotel.core.mapper.lombok;

import com.bookevhotel.core.dao.entity.HotelUser;
import com.bookevhotel.core.dto.HotelUserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HotelUserMapper extends BookEVHotelMapper<HotelUser, HotelUserDTO> {
}
