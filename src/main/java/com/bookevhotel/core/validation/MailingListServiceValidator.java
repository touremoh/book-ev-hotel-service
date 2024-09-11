package com.bookevhotel.core.validation;

import com.bookevhotel.core.annotation.ServiceValidator;
import com.bookevhotel.core.dto.MailingListDTO;
import com.bookevhotel.core.exception.BookEVHotelException;

@ServiceValidator
public class MailingListServiceValidator implements BookEVHotelServiceValidator<MailingListDTO> {
	@Override
	public void validateBeforeFindOne(MailingListDTO dto) throws BookEVHotelException {

	}

	@Override
	public void validateBeforeFindAll(MailingListDTO dto) throws BookEVHotelException {

	}

	@Override
	public void validateBeforeCreateOne(MailingListDTO dto) throws BookEVHotelException {

	}

	@Override
	public void validateBeforeUpdateOne(MailingListDTO dto) throws BookEVHotelException {

	}
}
