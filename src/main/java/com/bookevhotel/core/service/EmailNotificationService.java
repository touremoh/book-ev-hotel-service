package com.bookevhotel.core.service;

import com.bookevhotel.core.dto.HotelUserDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmailNotificationService {

	private final JavaMailSender mailSender;

	@Value("${constants.accountActivationLink}")
	private String accountActivationLink;

	@Autowired
	public EmailNotificationService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendEmail(HotelUserDTO receiver, String subject) throws BookEVHotelException {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

			// Set email parameters
			helper.setTo(receiver.getEmail());
			helper.setSubject(subject);

			// Update the activation link
			String activationLink = accountActivationLink.replace("{{userId}}", receiver.getId());

			// Load the HTML template from file
			String htmlTemplate = loadHtmlTemplate();

			// Replace placeholders with dynamic content
			String htmlContent = htmlTemplate
				.replace("{{website_name}}", "Book EV Hotel")
				.replace("{{username}}", receiver.getFirstName())
				.replace("{{activation_link}}", activationLink);

			// Set the HTML content in the email
			helper.setText(htmlContent, true);  // true = use HTML

			// Send the email
			mailSender.send(mimeMessage);
		} catch (MessagingException | IOException e) {
			log.error("Failed to send email notification to the user {}. Error message is: " + e.getMessage(), receiver.getEmail(), e);
			throw new BookEVHotelException(
				"Failed to send notification to user",
				e,
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR
			);
		}
	}


	private String loadHtmlTemplate() throws IOException {
		ClassPathResource resource = new ClassPathResource("templates/account-activation-template.html");
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
			return reader.lines().collect(Collectors.joining(System.lineSeparator()));
		}
	}
}
