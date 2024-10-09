package com.bookevhotel.core.validation;

import com.bookevhotel.core.annotation.ServiceValidator;
import com.bookevhotel.core.dto.SearchWordDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import org.springframework.http.HttpStatus;

import java.util.List;

import static java.util.Objects.isNull;

@ServiceValidator
public class SearchWordServiceValidator implements BookEVHotelServiceValidator<SearchWordDTO> {

	@Override
	public void validateBeforeFindOne(SearchWordDTO searchWordDTO) throws BookEVHotelException {
		if (isNull(searchWordDTO) || isNull(searchWordDTO.getId()) || isNull(searchWordDTO.getKey())) {
			throw new BookEVHotelException(
				"Mandatory fields not found",
				HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST
			);
		}
	}

	@Override
	public void validateBeforeCreateOne(SearchWordDTO searchWordDTO) throws BookEVHotelException {
		if (isNull(searchWordDTO)) {
			throw new BookEVHotelException(
				"Cannot persist empty search words",
				HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST
			);
		}
		if (isNull(searchWordDTO.getKey())) {
			throw new BookEVHotelException(
				"The key is required",
				HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST
			);
		}
		if (isNull(searchWordDTO.getValues()) || searchWordDTO.getValues().isEmpty()) {
			throw new BookEVHotelException(
				"The ID of the hotel is required",
				HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST
			);
		}
	}

	@Override
	public void validateBeforeUpdateOne(SearchWordDTO searchWordDTO) throws BookEVHotelException {
		if (isNull(searchWordDTO) || isNull(searchWordDTO.getId()) ) {
			throw new BookEVHotelException(
				"Cannot update search words without id",
				HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST
			);
		}
		this.validateBeforeCreateOne(searchWordDTO);
	}

	@Override
	public void validateBeforeFindAll(List<SearchWordDTO> searchWordDTOS) throws BookEVHotelException {
		for (SearchWordDTO searchWordDTO : searchWordDTOS) {
			if (isNull(searchWordDTO) || isNull(searchWordDTO.getKey())) {
				throw new BookEVHotelException(
					"Mandatory fields not found",
					HttpStatus.BAD_REQUEST.value(),
					HttpStatus.BAD_REQUEST
				);
			}
		}
	}
}
