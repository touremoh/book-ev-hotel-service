package com.bookevhotel.core.service;

import com.bookevhotel.core.dto.OTPCodeDTO;
import com.bookevhotel.core.dto.HotelUserDTO;
import com.bookevhotel.core.enums.EmailTitlesEnum;
import com.bookevhotel.core.exception.BookEVHotelException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AccountCreationNotificationService extends AbstractEmailService {

	private static final String SUBJECT = "Action Required: Activate Your Account with Book EV Hotel (Code Inside)";

	@Autowired
	public AccountCreationNotificationService(OAuth2TokenService oauth2TokenService) {
		super(oauth2TokenService);
	}

	public void sendNotification(HotelUserDTO receiver, OTPCodeDTO otp) throws BookEVHotelException {
		try {
			// Load the HTML template from file
			String htmlTemplate = loadHtmlTemplate("templates/account-activation-template.html");

			// Get language code
			String languageCode = receiver.getLanguageCode().toLowerCase();

			// Get the template content
			String templateContent = loadHtmlTemplate("templates/"+languageCode+"_notification_content.html");

			// Replace placeholders with dynamic content
			templateContent = templateContent
				.replace("{{year}}", String.valueOf(LocalDate.now().getYear()))
				.replace("{{website_name}}", "Book EV Hotel")
				.replace("{{first_name}}", receiver.getFirstName())
				.replace("{{activation_code}}", otp.getCode());

			// Update html template
			String htmlContent = htmlTemplate.replace("{{template_content}}", templateContent);

			// Send Email
			sendEmail(receiver.getEmail(), EmailTitlesEnum.getEmailTitle(languageCode), htmlContent);
		} catch (IOException | MailException e) {
			log.error("Failed to send email notification to the user {}. Error message is: {}", receiver.getEmail(), e.getMessage(), e);
			throw new BookEVHotelException(
				"Failed to send notification to user",
				e,
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR
			);
		}
	}

	private String loadHtmlTemplate(String filePath) throws IOException {
		ClassPathResource resource = new ClassPathResource(filePath);
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
			return reader.lines().collect(Collectors.joining(System.lineSeparator()));
		}
	}
}
