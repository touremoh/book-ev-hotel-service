package com.bookevhotel.core.mapper.requests;

import com.bookevhotel.core.annotation.RequestParamsMapper;
import com.bookevhotel.core.dto.AdminDashboardDTO;
import com.bookevhotel.core.dto.HotelDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import com.bookevhotel.core.utils.BookEVHotelUtils;

import java.util.Map;

@RequestParamsMapper
public class AdminDashboardRequestParamsMapper implements BookEVHotelRequestParamsMapper<AdminDashboardDTO> {
	@Override
	public AdminDashboardDTO map(Map<String, String> params) throws BookEVHotelException {
		BookEVHotelUtils.assertNotNull(params, "Http request params must not be null");

		var adminDashboardDTO = new AdminDashboardDTO();
		return adminDashboardDTO;
	}
}
