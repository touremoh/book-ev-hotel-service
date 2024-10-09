package com.bookevhotel.core.mapper.lombok;

import com.bookevhotel.core.dao.entity.SearchKeyword;
import com.bookevhotel.core.dto.SearchKeywordDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SearchKeywordMapper extends BookEVHotelMapper<SearchKeyword, SearchKeywordDTO> {
}
