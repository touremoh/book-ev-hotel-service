package com.bookevhotel.core.controller;

import com.bookevhotel.core.dto.SubscriptionPlanDTO;
import com.bookevhotel.core.service.SubscriptionPlanService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscription-plans")
public class SubscriptionPlanController extends AbstractBookEVHotelController<SubscriptionPlanDTO> {
	public SubscriptionPlanController(SubscriptionPlanService service) {
		super(service);
	}
}
