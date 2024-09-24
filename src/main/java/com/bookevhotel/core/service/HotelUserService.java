package com.bookevhotel.core.service;

import com.bookevhotel.core.dao.entity.HotelUser;
import com.bookevhotel.core.dao.repository.HotelUserRepositoryImpl;
import com.bookevhotel.core.dto.HotelUserDTO;
import com.bookevhotel.core.enums.UserRoleEnum;
import com.bookevhotel.core.enums.UserStatusEnum;
import com.bookevhotel.core.exception.BookEVHotelException;
import com.bookevhotel.core.mapper.lombok.HotelUserMapper;
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
	private final EmailNotificationService emailNotificationService;

	@Autowired
	public HotelUserService(
		HotelUserRepositoryImpl repository,
		HotelUserMapper mapper,
		HotelUserServiceValidator validator,
		PasswordEncoder passwordEncoder,
		EmailNotificationService emailNotificationService) {

		super(repository, mapper, validator);
		this.passwordEncoder = passwordEncoder;
		this.emailNotificationService = emailNotificationService;
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
		// Once the user created, send confirmation email to him to activate the user
		this.emailNotificationService.sendEmail(hotelUserDTO, "Please activate your account");
	}

	@Override
	protected void processBeforeUpdateOne(HotelUserDTO dto) throws BookEVHotelException {
		log.debug("Updating hotel user: {}", dto);

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

	public HotelUserDTO activateUserAccount(String userId) throws BookEVHotelException {
		log.debug("Activating hotel user: {}", userId);

		// Build user dto
		var hotelUserDTO = new HotelUserDTO();
		hotelUserDTO.setId(userId);
		hotelUserDTO.setUserStatus(UserStatusEnum.ACTIVE.getValue());

		// find the user
		var hotelUser = this.findOne(hotelUserDTO);

		// Check if user is active
		if (Objects.nonNull(hotelUser) && hotelUser.getUserStatus().equals(UserStatusEnum.ACTIVE.getValue())) {
			log.warn("User already active: {}", hotelUser);
			throw new BookEVHotelException(
				"Dead link: user already activated",
				HttpStatus.UNAUTHORIZED.value(),
				HttpStatus.UNAUTHORIZED
			);
		}

		// Activate user
		hotelUser.setUserStatus(UserStatusEnum.ACTIVE.getValue());

		// update user data and return the results
		return this.updateOne(hotelUser);
	}
}
