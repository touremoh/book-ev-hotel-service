package com.bookevhotel.core.validation;

import com.bookevhotel.core.annotation.ServiceValidator;
import com.bookevhotel.core.dto.HotelDTO;
import com.bookevhotel.core.exception.BookEVHotelException;

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

	}

	@Override
	public void validateBeforeCreateOne(HotelDTO dto) throws BookEVHotelException {

	}

	@Override
	public void validateBeforeUpdateOne(HotelDTO dto) throws BookEVHotelException {

	}
}
