package com.bookevhotel.core.validation;

import com.bookevhotel.core.annotation.ServiceValidator;
import com.bookevhotel.core.dto.SearchWordsDTO;
import com.bookevhotel.core.exception.BookEVHotelException;

@ServiceValidator
public class DictionaryServiceValidator implements BookEVHotelServiceValidator<SearchWordsDTO> {

	@Override
	public void validateBeforeFindOne(SearchWordsDTO dto) throws BookEVHotelException {

	}

	@Override
	public void validateBeforeFindAll(SearchWordsDTO dto) throws BookEVHotelException {

	}

	@Override
	public void validateBeforeCreateOne(SearchWordsDTO dto) throws BookEVHotelException {

	}

	@Override
	public void validateBeforeUpdateOne(SearchWordsDTO dto) throws BookEVHotelException {

	}
}
