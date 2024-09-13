package com.bookevhotel.core.validation;

import com.bookevhotel.core.annotation.ServiceValidator;
import com.bookevhotel.core.dto.HotelDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import com.bookevhotel.core.utils.BookEVHotelUtils;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.mapstruct.ap.internal.util.Strings.isEmpty;

@ServiceValidator
public class HotelServiceValidator implements BookEVHotelServiceValidator<HotelDTO> {
	@Override
	public void validateBeforeFindOne(HotelDTO dto) throws BookEVHotelException {
		if (isEmpty(dto.getId()) && isEmpty(dto.getHotelName()) && isEmpty(dto.getWebsiteLink())) {
			throw new BookEVHotelException("Mandatory fields 'hotelName, id, website link' are missing");
		}
	}

	@Override
	public void validateBeforeFindAll(HotelDTO dto) throws BookEVHotelException {
		if (isNull(dto) || BookEVHotelUtils.allNull(dto)) {
			throw new BookEVHotelException("Can't find hotel with missing find criteria");
		}
	}

	@Override
	public void validateBeforeCreateOne(HotelDTO dto) throws BookEVHotelException {
		if (isNull(dto)) {
			throw new BookEVHotelException("Can't create hotel with missing required fields");
		}
		if (nonNull(dto.getId())) {
			throw new BookEVHotelException("Illegal hotel id " + dto.getId());
		}
		if (BookEVHotelUtils.allNull(dto)) {
			throw new BookEVHotelException("Can't create hotel with missing required fields");
		}
	}

	@Override
	public void validateBeforeUpdateOne(HotelDTO dto) throws BookEVHotelException {
		if (isNull(dto) || isNull(dto.getId())) {
			throw new BookEVHotelException("Can't update hotel with missing required fields");
		}
		if (BookEVHotelUtils.allNull(dto)) {
			throw new BookEVHotelException("Can't update hotel with missing required fields");
		}
	}
}
