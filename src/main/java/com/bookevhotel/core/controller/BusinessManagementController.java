package com.bookevhotel.core.controller;

import com.bookevhotel.core.dto.AdminDashboardDTO;
import com.bookevhotel.core.mapper.requests.AdminDashboardRequestParamsMapper;
import com.bookevhotel.core.service.AdminDashboardService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business")
public class BusinessManagementController extends AbstractBookEVHotelController<AdminDashboardDTO> {
	public BusinessManagementController(AdminDashboardService service, AdminDashboardRequestParamsMapper requestParamsMapper) {
		super(service, requestParamsMapper);
	}

	// Add Hotel and Charging Information
	// Update Hotel and Charging Information
}
