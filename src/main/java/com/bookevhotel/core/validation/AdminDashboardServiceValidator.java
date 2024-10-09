package com.bookevhotel.core.validation;

import com.bookevhotel.core.annotation.ServiceValidator;
import com.bookevhotel.core.dto.AdminDashboardDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import org.mapstruct.ap.internal.util.Strings;

import java.util.Objects;

import static org.mapstruct.ap.internal.util.Strings.isEmpty;

@ServiceValidator
public class AdminDashboardServiceValidator implements BookEVHotelServiceValidator<AdminDashboardDTO> {

	@Override
	public void validateBeforeFindOne(AdminDashboardDTO dashboardDTO) throws BookEVHotelException {
		if (Objects.isNull(dashboardDTO)) {
			throw new BookEVHotelException("Mandatory field 'dashboardDTO' is required");
		}

		if (Strings.isEmpty(dashboardDTO.getId()) && Strings.isEmpty(dashboardDTO.getHotelId())) {
			throw new BookEVHotelException("Mandatory field 'dashboardDTO' is required for search");
		}
	}

	@Override
	public void validateBeforeCreateOne(AdminDashboardDTO dashboardDTO) throws BookEVHotelException {
		if (Strings.isEmpty(dashboardDTO.getHotelId())) {
			throw new BookEVHotelException("Hotel ID is required to create dashboard");
		}
	}

	@Override
	public void validateBeforeUpdateOne(AdminDashboardDTO dashboardDTO) throws BookEVHotelException {
		if (Strings.isEmpty(dashboardDTO.getId()) || Strings.isEmpty(dashboardDTO.getHotelId())) {
			throw new BookEVHotelException("ID and Hotel ID are required to update dashboard");
		}
	}
}
