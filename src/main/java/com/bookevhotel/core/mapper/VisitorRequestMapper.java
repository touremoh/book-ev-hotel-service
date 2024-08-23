package com.bookevhotel.core.mapper;

import com.bookevhotel.core.dao.entity.VisitorRequest;
import com.bookevhotel.core.dto.VisitorRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VisitorRequestMapper extends BookEVHotelMapper<VisitorRequest, VisitorRequestDTO> {
}
