package com.bookevhotel.core.validation;

import com.bookevhotel.core.annotation.ServiceValidator;
import com.bookevhotel.core.dto.DictionaryDTO;
import com.bookevhotel.core.exception.BookEVHotelException;

@ServiceValidator
public class DictionaryServiceValidator implements BookEVHotelServiceValidator<DictionaryDTO> {

	@Override
	public void validateBeforeFindOne(DictionaryDTO dto) throws BookEVHotelException {

	}

	@Override
	public void validateBeforeFindAll(DictionaryDTO dto) throws BookEVHotelException {

	}

	@Override
	public void validateBeforeCreateOne(DictionaryDTO dto) throws BookEVHotelException {

	}

	@Override
	public void validateBeforeUpdateOne(DictionaryDTO dto) throws BookEVHotelException {

	}
}
