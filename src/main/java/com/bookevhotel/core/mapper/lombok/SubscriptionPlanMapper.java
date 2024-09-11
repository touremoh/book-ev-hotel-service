package com.bookevhotel.core.mapper.lombok;

import com.bookevhotel.core.dao.entity.SubscriptionPlan;
import com.bookevhotel.core.dto.SubscriptionPlanDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubscriptionPlanMapper extends BookEVHotelMapper<SubscriptionPlan, SubscriptionPlanDTO> {
}
