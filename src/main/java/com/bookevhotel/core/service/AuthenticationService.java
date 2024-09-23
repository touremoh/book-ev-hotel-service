package com.bookevhotel.core.service;

import com.bookevhotel.core.config.JwtConfig;
import com.bookevhotel.core.dto.HotelUserDTO;
import com.bookevhotel.core.dto.JwtDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import com.bookevhotel.core.utils.JwtUtils;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
	private final ApplicationUserDetailsService userDetailsService;
	private final AuthenticationManager authenticationManager;
	private final JwtConfig jwtConfig;

	@Autowired
	public AuthenticationService(
		ApplicationUserDetailsService userDetailsService,
		AuthenticationManager authenticationManager,
		JwtConfig jwtConfig) {
		this.userDetailsService = userDetailsService;
		this.authenticationManager = authenticationManager;
		this.jwtConfig = jwtConfig;
	}

	public JwtDTO authenticate(HotelUserDTO hotelUserDTO) throws BookEVHotelException {
		try {
			// Authenticate by email and password
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(hotelUserDTO.getEmail(), hotelUserDTO.getRawPassword()));

			// Get the user details using the custom user details service
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(hotelUserDTO.getEmail());

			// Generate a JWT and return it
			return JwtDTO.builder().token(JwtUtils.generateToken(userDetails, jwtConfig)).build();
		} catch (AuthenticationException e) {
			throw new BookEVHotelException("Authentication failed", e);
		}
	}

	public Boolean isTokenValid(String authorizationHeader) throws BookEVHotelException {
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			String token = authorizationHeader.substring(7);
			if (Boolean.TRUE.equals(JwtUtils.isTokenExpired(token, jwtConfig.getSecretKey()))) {
				throw new BookEVHotelException("Token has expired", HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED);
			}
			return true;
		}
		return false;
	}

	public JwtDTO refreshToken(String authorizationHeader) throws BookEVHotelException {
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			String token = authorizationHeader.substring(7);
			try {
				// Extract the username
				String username = JwtUtils.extractUsername(token, this.jwtConfig.getSecretKey());

				// Load user details to issue a new token
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

				// Return the new access token
				return JwtUtils.generateAndBuildToken(userDetails, this.jwtConfig);

			} catch (JwtException ex) {
				throw new BookEVHotelException(ex.getMessage(), HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED);
			}
		}
		throw new BookEVHotelException("Invalid token", HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED);
	}
}
