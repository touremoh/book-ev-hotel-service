package com.bookevhotel.core.validation;

import com.bookevhotel.core.annotation.ServiceValidator;
import com.bookevhotel.core.dto.AdminDashboardDTO;
import com.bookevhotel.core.exception.BookEVHotelException;

@ServiceValidator
public class AdminDashboardServiceValidator implements BookEVHotelServiceValidator<AdminDashboardDTO> {

	@Override
	public void validateBeforeFindOne(AdminDashboardDTO dto) throws BookEVHotelException {

	}

	@Override
	public void validateBeforeFindAll(AdminDashboardDTO dto) throws BookEVHotelException {

	}

	@Override
	public void validateBeforeCreateOne(AdminDashboardDTO dto) throws BookEVHotelException {

	}

	@Override
	public void validateBeforeUpdateOne(AdminDashboardDTO dto) throws BookEVHotelException {

	}
}
