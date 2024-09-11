package com.bookevhotel.core.validation;

import com.bookevhotel.core.annotation.ServiceValidator;
import com.bookevhotel.core.dto.PaymentsInformationDTO;
import com.bookevhotel.core.exception.BookEVHotelException;

@ServiceValidator
public class PaymentsInformationServiceValidator implements BookEVHotelServiceValidator<PaymentsInformationDTO> {
	@Override
	public void validateBeforeFindOne(PaymentsInformationDTO dto) throws BookEVHotelException {

	}

	@Override
	public void validateBeforeFindAll(PaymentsInformationDTO dto) throws BookEVHotelException {

	}

	@Override
	public void validateBeforeCreateOne(PaymentsInformationDTO dto) throws BookEVHotelException {

	}

	@Override
	public void validateBeforeUpdateOne(PaymentsInformationDTO dto) throws BookEVHotelException {

	}
}
