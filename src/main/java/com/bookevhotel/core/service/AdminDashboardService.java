package com.bookevhotel.core.service;

import com.bookevhotel.core.dao.entity.AdminDashboard;
import com.bookevhotel.core.dao.repository.AdminDashboardRepositoryImpl;
import com.bookevhotel.core.dto.AdminDashboardDTO;
import com.bookevhotel.core.mapper.lombok.AdminDashboardMapper;
import com.bookevhotel.core.validation.AdminDashboardServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminDashboardService extends AbstractBookEVHotelService<AdminDashboard, AdminDashboardDTO>  {
	@Autowired
	public AdminDashboardService(AdminDashboardRepositoryImpl repository, AdminDashboardMapper mapper, AdminDashboardServiceValidator validator) {
		super(repository, mapper, validator);
	}
}
