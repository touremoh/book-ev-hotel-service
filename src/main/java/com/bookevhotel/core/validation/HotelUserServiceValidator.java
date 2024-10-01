package com.bookevhotel.core.validation;

import com.bookevhotel.core.annotation.ServiceValidator;
import com.bookevhotel.core.dto.HotelUserDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import java.util.regex.Pattern;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
@ServiceValidator
public class HotelUserServiceValidator implements BookEVHotelServiceValidator<HotelUserDTO> {

	@Value("${regex.email}")
	private String emailRegex;

	@Value("${regex.password}")
	private String passwordRegex;


	@Override
	public void validateBeforeFindOne(HotelUserDTO hotelUserDTO) throws BookEVHotelException {
		if (isNull(hotelUserDTO)) {
			throw new BookEVHotelException("Cannot find user - Mandatory fields are empty");
		}
		boolean isValid = Strings.isNotEmpty(hotelUserDTO.getId()) && Strings.isNotBlank(hotelUserDTO.getId());

		if (Strings.isNotEmpty(hotelUserDTO.getEmail()) && Strings.isNotBlank(hotelUserDTO.getEmail())) {
			isValid = true;
		}
		if (!isValid) {
			throw new BookEVHotelException(
				"Cannot find user - Mandatory fields are missing",
				HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST
			);
		}
	}

	@Override
	public void validateBeforeFindAll(HotelUserDTO dto) throws BookEVHotelException {
		log.debug("Validating request before find all");
	}

	@Override
	public void validateBeforeCreateOne(HotelUserDTO dto) throws BookEVHotelException {
		if (isNull(dto) || nonNull(dto.getId())) {
			throw new BookEVHotelException(
				"Invalid input - ID not allowed",
				HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST
			);
		}

		Pattern emailPattern = Pattern.compile(emailRegex);
		Pattern passwordPattern = Pattern.compile(passwordRegex);

		if (!emailPattern.matcher(dto.getEmail()).matches()) {
			throw new BookEVHotelException(
				"Invalid email address format",
				HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST
			);
		}

		if (!passwordPattern.matcher(dto.getRawPassword()).matches()) {
			throw new BookEVHotelException(
				"Invalid password format",
				HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST
			);
		}
	}

	@Override
	public void validateBeforeUpdateOne(HotelUserDTO dto) throws BookEVHotelException {
		if (isNull(dto) || isNull(dto.getId())) {
			throw new BookEVHotelException(
				"Invalid input - ID of the document to be updated is missing",
				HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST
			);
		}
	}
}
