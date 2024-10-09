package com.bookevhotel.core.validation;

import com.bookevhotel.core.annotation.ServiceValidator;
import com.bookevhotel.core.dto.SearchKeywordDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import org.springframework.http.HttpStatus;

import java.util.List;

import static java.util.Objects.isNull;

@ServiceValidator
public class SearchKeywordServiceValidator implements BookEVHotelServiceValidator<SearchKeywordDTO> {

	@Override
	public void validateBeforeFindOne(SearchKeywordDTO searchKeywordDTO) throws BookEVHotelException {
		if (isNull(searchKeywordDTO) || isNull(searchKeywordDTO.getId()) || isNull(searchKeywordDTO.getKey())) {
			throw new BookEVHotelException(
				"Mandatory fields not found",
				HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST
			);
		}
	}

	@Override
	public void validateBeforeCreateOne(SearchKeywordDTO searchKeywordDTO) throws BookEVHotelException {
		if (isNull(searchKeywordDTO)) {
			throw new BookEVHotelException(
				"Cannot persist empty search words",
				HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST
			);
		}
		if (isNull(searchKeywordDTO.getKey())) {
			throw new BookEVHotelException(
				"The key is required",
				HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST
			);
		}
		if (isNull(searchKeywordDTO.getValues()) || searchKeywordDTO.getValues().isEmpty()) {
			throw new BookEVHotelException(
				"The ID of the hotel is required",
				HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST
			);
		}
	}

	@Override
	public void validateBeforeUpdateOne(SearchKeywordDTO searchKeywordDTO) throws BookEVHotelException {
		if (isNull(searchKeywordDTO) || isNull(searchKeywordDTO.getId()) ) {
			throw new BookEVHotelException(
				"Cannot update search words without id",
				HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST
			);
		}
		this.validateBeforeCreateOne(searchKeywordDTO);
	}

	@Override
	public void validateBeforeFindAll(List<SearchKeywordDTO> searchKeywordDTOS) throws BookEVHotelException {
		for (SearchKeywordDTO searchKeywordDTO : searchKeywordDTOS) {
			if (isNull(searchKeywordDTO) || isNull(searchKeywordDTO.getKey())) {
				throw new BookEVHotelException(
					"Mandatory fields not found",
					HttpStatus.BAD_REQUEST.value(),
					HttpStatus.BAD_REQUEST
				);
			}
		}
	}
}
