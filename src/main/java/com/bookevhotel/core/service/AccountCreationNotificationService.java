package com.bookevhotel.core.service;

import com.bookevhotel.core.dto.AccountActivationCodeDTO;
import com.bookevhotel.core.dto.HotelUserDTO;
import com.bookevhotel.core.exception.BookEVHotelException;
import com.microsoft.graph.models.*;
import com.microsoft.graph.models.Message;
import com.microsoft.graph.serviceclient.GraphServiceClient;
import com.microsoft.graph.users.item.sendmail.SendMailPostRequestBody;
import com.microsoft.kiota.authentication.AuthenticationProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AccountCreationNotificationService {

	private final OAuth2TokenService oauth2TokenService;

	@Value("${spring.mail.username}")
	private String senderEmail;

	private static final String SUBJECT = "Action Required: Activate Your Account with Book EV Hotel (Code Inside)";

	@Autowired
	public AccountCreationNotificationService(OAuth2TokenService oauth2TokenService) {
		this.oauth2TokenService = oauth2TokenService;
	}

	public void sendEmail(HotelUserDTO receiver, AccountActivationCodeDTO accountActivationCode) throws BookEVHotelException {
		try {
			var recipient = new Recipient();
			var emailAddress = new EmailAddress();
			emailAddress.setAddress(receiver.getEmail());
			recipient.setEmailAddress(emailAddress);

			var message = new Message();
			message.setToRecipients(List.of(recipient));
			message.setSubject(SUBJECT);


			// Load the HTML template from file
			String htmlTemplate = loadHtmlTemplate();

			// Replace placeholders with dynamic content
			String htmlContent = htmlTemplate
				.replace("{{year}}", String.valueOf(LocalDate.now().getYear()))
				.replace("{{website_name}}", "Book EV Hotel")
				.replace("{{first_name}}", receiver.getFirstName())
				.replace("{{activation_code}}", accountActivationCode.getActivationCode());


			var itemBody = new ItemBody();
			itemBody.setContent(htmlContent);
			itemBody.setContentType(BodyType.Html);
			message.setBody(itemBody);

			var accessToken = this.oauth2TokenService.getAccessToken();

			AuthenticationProvider authProvider = (request, _) -> request.headers.put("Authorization", Set.of("Bearer " + accessToken));
			var graphClient = new GraphServiceClient(authProvider);

			var postRequest = new SendMailPostRequestBody();
			postRequest.setMessage(message);

			graphClient
				.users()
				.byUserId(this.senderEmail)
				.sendMail()
				.post(postRequest);
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

	private String loadHtmlTemplate() throws IOException {
		ClassPathResource resource = new ClassPathResource("templates/account-activation-template.html");
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
			return reader.lines().collect(Collectors.joining(System.lineSeparator()));
		}
	}
}
