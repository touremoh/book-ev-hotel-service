package com.bookevhotel.core.validation;

import com.bookevhotel.core.annotation.ServiceValidator;
import com.bookevhotel.core.dto.HotelLeadDTO;
import com.bookevhotel.core.exception.BookEVHotelException;

import java.util.Objects;

import static org.springframework.util.ObjectUtils.isEmpty;

@ServiceValidator
public class HotelLeadServiceValidator implements BookEVHotelServiceValidator<HotelLeadDTO> {
	@Override
	public void validateBeforeFindOne(HotelLeadDTO leadDTO) throws BookEVHotelException {
		if (Objects.isNull(leadDTO)) {
			throw new BookEVHotelException("Lead DTO cannot be null");
		}

		if (isEmpty(leadDTO.getId()) && isEmpty(leadDTO.getHotelId()) && isEmpty(leadDTO.getEmail())) {
			throw new BookEVHotelException("Mandatory field 'leadDTO' is required for search");
		}
	}

	@Override
	public void validateBeforeFindAll(HotelLeadDTO leadDTO) throws BookEVHotelException {
		if (Objects.isNull(leadDTO)) {
			throw new BookEVHotelException("Mandatory field 'leadDTO' is required");
		}

		if (isEmpty(leadDTO.getHotelId())) {
			throw new BookEVHotelException("Hotel ID is required to find all leads");
		}
	}

	@Override
	public void validateBeforeCreateOne(HotelLeadDTO leadDTO) throws BookEVHotelException {
		if (Objects.isNull(leadDTO)) {
			throw new BookEVHotelException("Mandatory field 'leadDTO' is required");
		}

		if (isEmpty(leadDTO.getHotelId()) && isEmpty(leadDTO.getEmail()) && isEmpty(leadDTO.getFullName())) {
			throw new BookEVHotelException("Mandatory field 'leadDTO' is required");
		}
	}

	@Override
	public void validateBeforeUpdateOne(HotelLeadDTO leadDTO) throws BookEVHotelException {
		if (Objects.isNull(leadDTO)) {
			throw new BookEVHotelException("Mandatory field 'leadDTO' is required");
		}

		if (isEmpty(leadDTO.getId()) && isEmpty(leadDTO.getHotelId())) {
			throw new BookEVHotelException("Mandatory field 'leadDTO' is required");
		}
	}
}
