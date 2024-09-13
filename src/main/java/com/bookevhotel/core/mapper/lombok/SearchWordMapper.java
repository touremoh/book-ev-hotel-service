package com.bookevhotel.core.mapper.lombok;

import com.bookevhotel.core.dao.entity.SearchWord;
import com.bookevhotel.core.dto.SearchWordsDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SearchWordMapper extends BookEVHotelMapper<SearchWord, SearchWordsDTO> {
}
