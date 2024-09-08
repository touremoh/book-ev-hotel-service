package com.bookevhotel.core.controller;

import com.bookevhotel.core.dto.AdminDashboardDTO;
import com.bookevhotel.core.service.AdminDashboardService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business")
public class AdminDashboardController extends AbstractBookEVHotelController<AdminDashboardDTO> {
	public AdminDashboardController(AdminDashboardService service) {
		super(service);
	}
}
