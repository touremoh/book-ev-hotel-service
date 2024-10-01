package com.bookevhotel.core.validation;

import com.bookevhotel.core.annotation.ServiceValidator;
import com.bookevhotel.core.dto.AccountActivationCodeDTO;
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
public class AccountActivationCodeValidation implements BookEVHotelServiceValidator<AccountActivationCodeDTO> {

	@Value("${regex.activationCode}")
	private String activationCodeRegex;

	@Override
	public void validateBeforeFindOne(AccountActivationCodeDTO activationCode) throws BookEVHotelException {
		if (isNull(activationCode)) {
			throw new BookEVHotelException("Cannot find code - Mandatory fields are empty");
		}
		boolean isValid = Strings.isNotEmpty(activationCode.getId()) && Strings.isNotBlank(activationCode.getId());

		if (Strings.isNotEmpty(activationCode.getActivationCode()) && Strings.isNotBlank(activationCode.getActivationCode())) {
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
	public void validateBeforeFindAll(AccountActivationCodeDTO activationCode) throws BookEVHotelException {
		throw new UnsupportedOperationException("Operation not supported");
	}

	@Override
	public void validateBeforeFindAll(List<AccountActivationCodeDTO> dtos) throws BookEVHotelException {
		throw new UnsupportedOperationException("Operation not supported");
	}


	@Override
	public void validateBeforeCreateOne(AccountActivationCodeDTO activationCode) throws BookEVHotelException {
		if (isNull(activationCode) || nonNull(activationCode.getId())) {
			throw new BookEVHotelException(
				"Invalid input - ID not allowed",
				HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST
			);
		}

		if (Strings.isNotEmpty(activationCode.getActivationCode())) {
			Pattern pattern = Pattern.compile(activationCodeRegex);
			if (!pattern.matcher(activationCode.getActivationCode()).matches()) {
				throw new BookEVHotelException(
					"Invalid activation code",
					HttpStatus.FORBIDDEN.value(),
					HttpStatus.FORBIDDEN
				);
			}
		}
	}

	@Override
	public void validateBeforeUpdateOne(AccountActivationCodeDTO activationCode) throws BookEVHotelException {
		throw new UnsupportedOperationException("Operation not supported");
	}
}
