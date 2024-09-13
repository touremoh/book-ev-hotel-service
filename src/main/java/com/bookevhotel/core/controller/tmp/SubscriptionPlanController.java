package com.bookevhotel.core.controller.tmp;

import com.bookevhotel.core.controller.AbstractBookEVHotelController;
import com.bookevhotel.core.dto.SubscriptionPlanDTO;
import com.bookevhotel.core.mapper.requests.SubscriptionPlanRequestParamsMapper;
import com.bookevhotel.core.service.SubscriptionPlanService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscription-plans")
public class SubscriptionPlanController extends AbstractBookEVHotelController<SubscriptionPlanDTO> {
	public SubscriptionPlanController(SubscriptionPlanService service, SubscriptionPlanRequestParamsMapper rpm) {
		super(service, rpm);
	}
}
