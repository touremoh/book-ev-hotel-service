package com.bookevhotel.core.service;

import com.bookevhotel.core.dao.entity.HotelUser;
import com.bookevhotel.core.dao.repository.HotelUserRepositoryImpl;
import com.bookevhotel.core.dto.OTPCodeDTO;
import com.bookevhotel.core.dto.AccountActivationRequest;
import com.bookevhotel.core.dto.HotelUserDTO;
import com.bookevhotel.core.enums.UserRoleEnum;
import com.bookevhotel.core.enums.UserStatusEnum;
import com.bookevhotel.core.exception.BookEVHotelException;
import com.bookevhotel.core.mapper.lombok.HotelUserMapper;
import com.bookevhotel.core.utils.BookEVHotelUtils;
import com.bookevhotel.core.utils.SecretGenerator;
import com.bookevhotel.core.validation.HotelUserServiceValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class HotelUserService extends AbstractBookEVHotelService<HotelUser, HotelUserDTO> {

	private final PasswordEncoder passwordEncoder;
	private final AccountCreationNotificationService notificationService;
	private final OTPCodeService otpCodeService;

	@Autowired
	public HotelUserService(
		HotelUserRepositoryImpl repository,
		HotelUserMapper mapper,
		HotelUserServiceValidator validator,
		PasswordEncoder passwordEncoder,
		AccountCreationNotificationService notificationService, OTPCodeService otpCodeService) {

		super(repository, mapper, validator);
		this.passwordEncoder = passwordEncoder;
		this.notificationService = notificationService;
		this.otpCodeService = otpCodeService;
	}

	@Override
	protected void processBeforeCreateOne(HotelUserDTO hotelUserDTO) throws BookEVHotelException {
		// Before Create => Check user existence
		if (this.repository.exists(this.mapper.map(hotelUserDTO))) {
			throw new BookEVHotelException(
				"HotelUser already exists",
				HttpStatus.NOT_ACCEPTABLE.value(),
				HttpStatus.NOT_ACCEPTABLE
			);
		}

		// Encode user's password (no check needed since it has already been checked)
		hotelUserDTO.setEncodedPassword(passwordEncoder.encode(hotelUserDTO.getRawPassword()));

		// Define user's role if not present
		if (Strings.isEmpty(hotelUserDTO.getUserStatus()) || Strings.isBlank(hotelUserDTO.getUserStatus())) {
			hotelUserDTO.setUserRole(UserRoleEnum.OWNER.getValue());
		}

		// Set the status to WAITING_FOR_REGISTRATION_VALIDATION
		hotelUserDTO.setUserStatus(UserStatusEnum.WAITING_FOR_REGISTRATION_VALIDATION.getValue());
	}

	@Override
	protected void processAfterCreateOne(HotelUserDTO hotelUserDTO) throws BookEVHotelException {
		// Generate OTP Secret
		var otpSecret = SecretGenerator.generateSecret(32);
		// Generate an activation code
		var code = this.otpCodeService.generateTOTP(otpSecret);

		// Save it to db
		var otpCodeDTO = OTPCodeDTO.builder()
			.code(code)
			.userId(hotelUserDTO.getId())
			.secret(otpSecret)
			.build();

		// Save
		this.otpCodeService.createOne(otpCodeDTO);

		// Ask the user to use the confirmation code to activate his account
		this.notificationService.sendEmail(hotelUserDTO, otpCodeDTO);
	}

	@Override
	protected void processBeforeUpdateOne(HotelUserDTO dto) throws BookEVHotelException {
		log.debug("Updating hotel user: {}", dto.getId());

		// Check if user exist by ID
		if (!repository.exists(this.mapper.map(dto))) {
			throw new BookEVHotelException(
				"Trying to update a hotel user that does not exist",
				HttpStatus.UNAUTHORIZED.value(),
				HttpStatus.UNAUTHORIZED
			);
		}

		// Update the DTO with the entity data
		this.mapper.merge(dto, this.findOne(dto));
	}

	public Boolean activateUserAccount(AccountActivationRequest request) throws BookEVHotelException {
		log.debug("Activating user account with code: {}", request.getCode());

		var code = request.getCode();

		// Find activation code in db
		var otpCodeToFind = OTPCodeDTO.builder().code(code).build();
		var otpCodeFound = this.otpCodeService.findOne(otpCodeToFind);

		// Check if code has not expired (valid)
		if (Objects.nonNull(otpCodeFound) && !this.otpCodeService.validateOTP(code, otpCodeFound.getSecret())) {
			log.warn("OTP code {} expired", code);
			throw new BookEVHotelException(
				"The OTP has expired",
				HttpStatus.EXPECTATION_FAILED.value(),
				HttpStatus.EXPECTATION_FAILED
			);
		}

		// find the user of the code
		var hotelUserDTO = new HotelUserDTO();
		hotelUserDTO.setId(otpCodeFound.getUserId());

		// Find
		var userFound = this.findOne(hotelUserDTO);

		// Check if user was found
		if (Objects.isNull(userFound)) {
			log.warn("User with id {} not found during activation", hotelUserDTO.getId());
			throw new BookEVHotelException(
				"User with id " + hotelUserDTO.getId() + " not found",
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR
			);
		}

		// Activate his status
		userFound.setUserStatus(UserStatusEnum.ACTIVE.getValue());

		// Update the user status in db
		this.updateOne(userFound);

		// return true if everything was fine
		return true;
	}
}
