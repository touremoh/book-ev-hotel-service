package com.bookevhotel.core.validation;

import com.bookevhotel.core.annotation.ServiceValidator;
import com.bookevhotel.core.dto.VisitorSearchDTO;
import com.bookevhotel.core.exception.BookEVHotelException;

@ServiceValidator
public class VisitorSearchServiceValidator implements BookEVHotelServiceValidator<VisitorSearchDTO> {
	@Override
	public void validateBeforeFindOne(VisitorSearchDTO dto) throws BookEVHotelException {

	}

	@Override
	public void validateBeforeFindAll(VisitorSearchDTO dto) throws BookEVHotelException {

	}

	@Override
	public void validateBeforeCreateOne(VisitorSearchDTO dto) throws BookEVHotelException {

	}

	@Override
	public void validateBeforeUpdateOne(VisitorSearchDTO dto) throws BookEVHotelException {

	}
}
