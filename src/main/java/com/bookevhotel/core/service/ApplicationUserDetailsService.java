package com.bookevhotel.core.service;

import com.bookevhotel.core.dao.entity.HotelUser;
import com.bookevhotel.core.dao.repository.HotelUserRepositoryImpl;
import com.bookevhotel.core.enums.UserStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

	private final HotelUserRepositoryImpl userRepository;

	@Autowired
	public ApplicationUserDetailsService(HotelUserRepositoryImpl userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		try {
			// Set up userDTO
			var userToFind = new HotelUser();
			userToFind.setEmail(email);

			// Find user by email
			var userFound = this.userRepository.findOne(userToFind);

			// Convert userDTO into Spring Security User
			return User
					.builder()
						.username(userFound.getEmail())
						.password(userFound.getEncodedPassword())
						.disabled(userFound.getUserStatus().equals(UserStatusEnum.INACTIVE.getValue()))
						.accountLocked(isAccountLocked(userFound))
						.roles(userFound.getUserRole())
						.authorities(new ArrayList<>())
					.build();
		} catch (AuthenticationException e) {
			throw new UsernameNotFoundException("An error occurred while trying to find the user", e);
		}
	}

	private boolean isAccountLocked(HotelUser userDTO) {
		return userDTO.getUserStatus().equals(UserStatusEnum.WAITING_FOR_REGISTRATION_VALIDATION.getValue());
	}
}
