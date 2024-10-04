package com.bookevhotel.core.service;

import com.bookevhotel.core.dao.entity.OTPCode;
import com.bookevhotel.core.dao.repository.OTPCodeRepositoryImpl;
import com.bookevhotel.core.dto.OTPCodeDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import com.bookevhotel.core.mapper.lombok.OTPCodeMapper;
import com.bookevhotel.core.validation.OTPCodeValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@Service
public class OTPCodeService extends AbstractBookEVHotelService<OTPCode, OTPCodeDTO> {

	private static final int OTP_LENGTH = 8; // Length of the OTP
	private static final String OTP_ALGORITHM = "HmacSHA1";

	protected OTPCodeService(OTPCodeRepositoryImpl repository, OTPCodeMapper mapper, OTPCodeValidation validator) {
		super(repository, mapper, validator);
	}

	// Method to generate a TOTP (Time-based OTP)
	public String generateTOTP(String secretKey) throws BookEVHotelException {
		// Get the current time in seconds divided by the time step (usually 30 seconds)
		long timeStep = 1800;
		long timeIndex = System.currentTimeMillis() / 1000L / timeStep;

		return generateHOTP(secretKey, timeIndex);
	}

	// Method to generate an HOTP (HMAC-based OTP)
	private String generateHOTP(String secretKey, long counter) throws BookEVHotelException {
		try {
			// Decode the secret key from Base32 or Base64
			byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
			SecretKeySpec signKey = new SecretKeySpec(keyBytes, OTP_ALGORITHM);

			// Convert the counter to a byte array
			ByteBuffer buffer = ByteBuffer.allocate(8);
			buffer.putLong(counter);
			byte[] counterBytes = buffer.array();

			// Initialize HMAC with SHA1
			Mac mac = Mac.getInstance(OTP_ALGORITHM);
			mac.init(signKey);

			// Perform HMAC on the counter bytes
			byte[] hash = mac.doFinal(counterBytes);

			// Use the last nibble of the hash to determine the offset
			int offset = hash[hash.length - 1] & 0xf;

			// Extract a 4-byte binary code from the hash
			int binaryCode = ((hash[offset] & 0x7f) << 24) |
				((hash[offset + 1] & 0xff) << 16) |
				((hash[offset + 2] & 0xff) << 8) |
				(hash[offset + 3] & 0xff);

			// Generate the OTP by taking the binary code modulo 10^OTP_LENGTH
			int otp = binaryCode % (int) Math.pow(10, OTP_LENGTH);

			// Ensure the OTP has the required length, padding with zeros if necessary
			return String.format("%0" + OTP_LENGTH + "d", otp);

		} catch (Exception e) {
			throw new BookEVHotelException(
				"Failed to generate OTP",
				e,
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR
			);
		}
	}

	// Example method to encode the secret key (can use Base32/Base64 encoding for storing)
	public String encodeSecret(String secret) {
		return Base64.getEncoder().encodeToString(secret.getBytes(StandardCharsets.UTF_8));
	}

	public boolean validateOTP(String inputOTP, String secretKey) throws BookEVHotelException {
		String generatedOTP = generateTOTP(secretKey);
		return inputOTP.equals(generatedOTP);
	}
}
