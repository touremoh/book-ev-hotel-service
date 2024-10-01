package com.bookevhotel.core.service;

import com.bookevhotel.core.dao.entity.HotelUser;
import com.bookevhotel.core.dao.repository.HotelUserRepositoryImpl;
import com.bookevhotel.core.dto.AccountActivationCodeDTO;
import com.bookevhotel.core.dto.ActivationCodeDTO;
import com.bookevhotel.core.dto.HotelUserDTO;
import com.bookevhotel.core.enums.UserRoleEnum;
import com.bookevhotel.core.enums.UserStatusEnum;
import com.bookevhotel.core.exception.BookEVHotelException;
import com.bookevhotel.core.mapper.lombok.HotelUserMapper;
import com.bookevhotel.core.utils.BookEVHotelUtils;
import com.bookevhotel.core.validation.HotelUserServiceValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Service
public class HotelUserService extends AbstractBookEVHotelService<HotelUser, HotelUserDTO> {

	private final PasswordEncoder passwordEncoder;
	private final AccountCreationNotificationService notificationService;
	private final ActivationCodeService activationCodeService;
	private static final long ACTIVATION_CODE_EXPIRATION_TIME = 30;

	@Autowired
	public HotelUserService(
		HotelUserRepositoryImpl repository,
		HotelUserMapper mapper,
		HotelUserServiceValidator validator,
		PasswordEncoder passwordEncoder,
		AccountCreationNotificationService notificationService, ActivationCodeService activationCodeService) {

		super(repository, mapper, validator);
		this.passwordEncoder = passwordEncoder;
		this.notificationService = notificationService;
		this.activationCodeService = activationCodeService;
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
		// Generate an activation code
		var code = BookEVHotelUtils.generateActivationCode();

		// Save it to db
		var accountActivationCodeDTO = AccountActivationCodeDTO.builder()
			.activationCode(code)
			.userId(hotelUserDTO.getId())
			.expirationTimestamp(LocalDateTime.now().plusMinutes(ACTIVATION_CODE_EXPIRATION_TIME))
			.build();

		// Check if code exist before saving (prevent collision)


		// Save
		this.activationCodeService.createOne(accountActivationCodeDTO);

		// Ask the user to use the confirmation code to activate his account
		this.notificationService.sendEmail(hotelUserDTO, accountActivationCodeDTO);
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

	public Boolean activateUserAccount(ActivationCodeDTO activationCode) throws BookEVHotelException {
		log.debug("Activating user account with code: {}", activationCode.getCode());

		var code = activationCode.getCode();

		// Find activation code in db
		var activationCodeToFind = AccountActivationCodeDTO.builder().activationCode(code).build();
		var activationCodeFound = this.activationCodeService.findOne(activationCodeToFind);

		// Check if code has not expired (valid)
		if (Objects.nonNull(activationCodeFound) && LocalDateTime.now().isAfter(activationCodeFound.getExpirationTimestamp())) {
			log.warn("Activation code {} expired", code);
			throw new BookEVHotelException(
				"The activation code has expired",
				HttpStatus.EXPECTATION_FAILED.value(),
				HttpStatus.EXPECTATION_FAILED
			);
		}

		// find the user of the code
		var hotelUserDTO = new HotelUserDTO();
		hotelUserDTO.setId(activationCodeFound.getUserId());

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

		// Delete activation code in db
		this.activationCodeService.deleteOne(activationCodeFound);

		// return true if everything was fine
		return true;
	}
}
