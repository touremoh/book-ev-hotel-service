package com.bookevhotel.core.service;

import com.bookevhotel.core.dao.entity.SubscriptionPlan;
import com.bookevhotel.core.dao.repository.SubscriptionPlanRepositoryImpl;
import com.bookevhotel.core.dto.SubscriptionPlanDTO;
import com.bookevhotel.core.mapper.lombok.SubscriptionPlanMapper;
import com.bookevhotel.core.validation.SubscriptionPlanServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionPlanService extends AbstractBookEVHotelService<SubscriptionPlan, SubscriptionPlanDTO> {
	@Autowired
	public SubscriptionPlanService(SubscriptionPlanRepositoryImpl repository, SubscriptionPlanMapper mapper, SubscriptionPlanServiceValidator validator) {
		super(repository, mapper, validator);
	}
}
