package com.bookevhotel.core.mapper;

import com.bookevhotel.core.dao.entity.Dictionary;
import com.bookevhotel.core.dto.DictionaryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DictionaryMapper extends BookEVHotelMapper<Dictionary, DictionaryDTO> {
}
