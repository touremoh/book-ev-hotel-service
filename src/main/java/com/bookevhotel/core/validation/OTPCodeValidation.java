package com.bookevhotel.core.validation;

import com.bookevhotel.core.annotation.ServiceValidator;
import com.bookevhotel.core.dto.OTPCodeDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
@ServiceValidator
public class OTPCodeValidation implements BookEVHotelServiceValidator<OTPCodeDTO> {

	@Value("${regex.activationCode}")
	private String activationCodeRegex;

	@Override
	public void validateBeforeFindOne(OTPCodeDTO otp) throws BookEVHotelException {
		if (isNull(otp)) {
			throw new BookEVHotelException("Cannot find code - Mandatory fields are empty");
		}
		boolean isValid = Strings.isNotEmpty(otp.getId()) && Strings.isNotBlank(otp.getId());

		if (Strings.isNotEmpty(otp.getCode()) && Strings.isNotBlank(otp.getCode())) {
			isValid = true;
		}
		if (!isValid) {
			throw new BookEVHotelException(
				"Cannot find code - Mandatory fields are missing",
				HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST
			);
		}
	}

	@Override
	public void validateBeforeFindAll(OTPCodeDTO activationCode) throws BookEVHotelException {
		throw new UnsupportedOperationException("Operation not supported");
	}

	@Override
	public void validateBeforeFindAll(List<OTPCodeDTO> dtos) throws BookEVHotelException {
		throw new UnsupportedOperationException("Operation not supported");
	}


	@Override
	public void validateBeforeCreateOne(OTPCodeDTO otp) throws BookEVHotelException {
		if (isNull(otp) || nonNull(otp.getId())) {
			throw new BookEVHotelException(
				"Invalid input - ID not allowed",
				HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST
			);
		}

		if (Strings.isNotEmpty(otp.getCode())) {
			Pattern pattern = Pattern.compile(activationCodeRegex);
			if (!pattern.matcher(otp.getCode()).matches()) {
				throw new BookEVHotelException(
					"Invalid activation code",
					HttpStatus.FORBIDDEN.value(),
					HttpStatus.FORBIDDEN
				);
			}
		}
	}

	@Override
	public void validateBeforeUpdateOne(OTPCodeDTO activationCode) throws BookEVHotelException {
		throw new UnsupportedOperationException("Operation not supported");
	}
}
