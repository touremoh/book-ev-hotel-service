package com.bookevhotel.core.service;

import com.bookevhotel.core.exception.BookEVHotelException;
import com.microsoft.graph.models.*;
import com.microsoft.graph.serviceclient.GraphServiceClient;
import com.microsoft.graph.users.item.sendmail.SendMailPostRequestBody;
import com.microsoft.kiota.authentication.AuthenticationProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;

import java.util.List;
import java.util.Set;

@Slf4j
public abstract class AbstractEmailService {

	protected final OAuth2TokenService oauth2TokenService;

	@Value("${spring.mail.username}")
	private String senderEmail;

	protected AbstractEmailService(OAuth2TokenService oauth2TokenService) {
		this.oauth2TokenService = oauth2TokenService;
	}


	public void sendEmail(String receiverEmail, String subject, String content) throws BookEVHotelException {
		try {
			var recipient = new Recipient();
			var emailAddress = new EmailAddress();
			emailAddress.setAddress(receiverEmail);
			recipient.setEmailAddress(emailAddress);

			var message = new Message();
			message.setToRecipients(List.of(recipient));
			message.setSubject(subject);


			var itemBody = new ItemBody();
			itemBody.setContent(content);
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
		} catch (MailException e) {
			log.error("Failed to send email to user {}. Error message is: {}", receiverEmail, e.getMessage(), e);
			throw new BookEVHotelException(
				"Failed to send email to user",
				e,
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR
			);
		}
	}
}
