package com.bookevhotel.core.validation;

import com.bookevhotel.core.annotation.ServiceValidator;
import com.bookevhotel.core.dto.SubscriptionPlanDTO;
import com.bookevhotel.core.exception.BookEVHotelException;

@ServiceValidator
public class SubscriptionPlanServiceValidator implements BookEVHotelServiceValidator<SubscriptionPlanDTO> {
	@Override
	public void validateBeforeFindOne(SubscriptionPlanDTO dto) throws BookEVHotelException {

	}

	@Override
	public void validateBeforeFindAll(SubscriptionPlanDTO dto) throws BookEVHotelException {

	}

	@Override
	public void validateBeforeCreateOne(SubscriptionPlanDTO dto) throws BookEVHotelException {

	}

	@Override
	public void validateBeforeUpdateOne(SubscriptionPlanDTO dto) throws BookEVHotelException {

	}
}
