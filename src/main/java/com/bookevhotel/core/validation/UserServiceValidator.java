package com.bookevhotel.core.validation;

import com.bookevhotel.core.annotation.ServiceValidator;
import com.bookevhotel.core.dto.UserDTO;
import com.bookevhotel.core.exception.BookEVHotelException;

@ServiceValidator
public class UserServiceValidator implements BookEVHotelServiceValidator<UserDTO> {
	@Override
	public void validateBeforeFindOne(UserDTO dto) throws BookEVHotelException {

	}

	@Override
	public void validateBeforeFindAll(UserDTO dto) throws BookEVHotelException {

	}

	@Override
	public void validateBeforeCreateOne(UserDTO dto) throws BookEVHotelException {

	}

	@Override
	public void validateBeforeUpdateOne(UserDTO dto) throws BookEVHotelException {

	}
}
