package com.bookevhotel.core.mapper.lombok;

import com.bookevhotel.core.dao.entity.VisitorSearch;
import com.bookevhotel.core.dto.VisitorSearchDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VisitorSearchMapper extends BookEVHotelMapper<VisitorSearch, VisitorSearchDTO> {
}
