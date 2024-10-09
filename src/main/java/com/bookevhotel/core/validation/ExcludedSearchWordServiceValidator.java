package com.bookevhotel.core.validation;

import com.bookevhotel.core.annotation.ServiceValidator;
import com.bookevhotel.core.dto.ExcludedSearchWordDTO;
import com.bookevhotel.core.dto.SearchWordDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import com.bookevhotel.core.utils.BookEVHotelUtils;
import org.springframework.http.HttpStatus;

import static java.util.Objects.isNull;

@ServiceValidator
public class ExcludedSearchWordServiceValidator implements BookEVHotelServiceValidator<ExcludedSearchWordDTO> {
	@Override
	public void validateBeforeFindAll(ExcludedSearchWordDTO dto) throws BookEVHotelException {
		if (isNull(dto) || BookEVHotelUtils.allNull(dto)) {
			throw new BookEVHotelException("Can't find ExcludedSearchWordDTO with missing find criteria");
		}
	}
}
