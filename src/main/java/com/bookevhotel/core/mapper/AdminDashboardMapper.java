package com.bookevhotel.core.mapper;

import com.bookevhotel.core.dao.entity.AdminDashboard;
import com.bookevhotel.core.dto.AdminDashboardDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminDashboardMapper extends BookEVHotelMapper<AdminDashboard, AdminDashboardDTO> {
}
