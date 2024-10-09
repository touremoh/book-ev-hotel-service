package com.bookevhotel.core.mapper.lombok;

import com.bookevhotel.core.dao.entity.ExcludedSearchKeyword;
import com.bookevhotel.core.dto.ExcludedSearchWordDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExcludedSearchKeywordMapper extends BookEVHotelMapper<ExcludedSearchKeyword, ExcludedSearchWordDTO> {
}
