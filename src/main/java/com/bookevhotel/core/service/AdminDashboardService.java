package com.bookevhotel.core.service;

import com.bookevhotel.core.dao.entity.AdminDashboard;
import com.bookevhotel.core.dao.repository.AdminDashboardRepository;
import com.bookevhotel.core.dto.AdminDashboardDTO;
import com.bookevhotel.core.mapper.AdminDashboardMapper;
import org.springframework.stereotype.Service;

@Service
public class AdminDashboardService extends AbstractBookEVHotelService<AdminDashboard, AdminDashboardDTO>  {
	public AdminDashboardService(AdminDashboardRepository repository, AdminDashboardMapper mapper) {
		super(repository, mapper);
	}
}
