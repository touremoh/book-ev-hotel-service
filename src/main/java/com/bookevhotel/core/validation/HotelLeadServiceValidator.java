package com.bookevhotel.core.validation;

import com.bookevhotel.core.annotation.ServiceValidator;
import com.bookevhotel.core.dto.HotelLeadDTO;
import com.bookevhotel.core.exception.BookEVHotelException;

@ServiceValidator
public class HotelLeadServiceValidator implements BookEVHotelServiceValidator<HotelLeadDTO> {
	@Override
	public void validateBeforeFindOne(HotelLeadDTO dto) throws BookEVHotelException {

	}

	@Override
	public void validateBeforeFindAll(HotelLeadDTO dto) throws BookEVHotelException {

	}

	@Override
	public void validateBeforeCreateOne(HotelLeadDTO dto) throws BookEVHotelException {

	}

	@Override
	public void validateBeforeUpdateOne(HotelLeadDTO dto) throws BookEVHotelException {

	}
}
