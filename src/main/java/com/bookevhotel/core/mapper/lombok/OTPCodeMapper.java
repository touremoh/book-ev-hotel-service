package com.bookevhotel.core.mapper.lombok;

import com.bookevhotel.core.dao.entity.OTPCode;
import com.bookevhotel.core.dto.OTPCodeDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OTPCodeMapper extends BookEVHotelMapper<OTPCode, OTPCodeDTO> {
}
