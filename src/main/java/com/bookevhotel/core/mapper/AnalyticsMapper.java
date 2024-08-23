package com.bookevhotel.core.mapper;

import com.bookevhotel.core.dao.entity.Analytics;
import com.bookevhotel.core.dto.AnalyticsDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnalyticsMapper extends BookEVHotelMapper<Analytics, AnalyticsDTO> {
}
