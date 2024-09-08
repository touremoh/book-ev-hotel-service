package com.bookevhotel.core.service;

import com.bookevhotel.core.dao.entity.SubscriptionPlan;
import com.bookevhotel.core.dao.repository.SubscriptionPlanRepository;
import com.bookevhotel.core.dto.SubscriptionPlanDTO;
import com.bookevhotel.core.mapper.SubscriptionPlanMapper;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionPlanService extends AbstractBookEVHotelService<SubscriptionPlan, SubscriptionPlanDTO> {
	public SubscriptionPlanService(SubscriptionPlanRepository repository, SubscriptionPlanMapper mapper) {
		super(repository, mapper);
	}
}
