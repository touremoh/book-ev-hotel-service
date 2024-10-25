package com.bookevhotel.core.service;

import com.bookevhotel.core.dto.HotelDTO;
import com.bookevhotel.core.dto.HotelLeadDTO;
import com.bookevhotel.core.dto.common.Offer;
import com.bookevhotel.core.enums.EmailTitlesEnum;
import com.bookevhotel.core.exception.BookEVHotelException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;

@Slf4j
@Service
public class HotelLeadNotificationService extends AbstractEmailService {

	@Autowired
	protected HotelLeadNotificationService(OAuth2TokenService oauth2TokenService) {
		super(oauth2TokenService);
	}

	public void sendNotification(HotelLeadDTO receiver, HotelDTO hotelDTO) throws BookEVHotelException {
		try {
			// Load the HTML template from file
			String htmlTemplate = loadHtmlTemplate("templates/lead-generation/lead-notification-template.html");

			// Get language code
			String languageCode = receiver.getLanguageCode().toLowerCase();

			// Get the template content
			String templateContent = loadHtmlTemplate("templates/lead-generation/"+languageCode+"_notification_content.html");

			// Replace placeholders with dynamic content
			templateContent = templateContent
				.replace("{{year}}", String.valueOf(LocalDate.now().getYear()))
				.replace("{{full_name}}", receiver.getFullName())
				.replace("{{offer_title}}", receiver.getRequestedOffer().getTitle())
				.replace("{{offer_code}}", receiver.getRequestedOffer().getCode())
				.replace("{{hotel_name}}", hotelDTO.getHotelName())
				.replace("{{hotel_address}}", hotelDTO.getLocation().toString())
				.replace("{{offer_start_date}}",  receiver.getRequestedOffer().getStartDate().toString())
				.replace("{{offer_end_date}}", receiver.getRequestedOffer().getStartDate().toString());

			// Update html template
			String htmlContent = htmlTemplate.replace("{{template_content}}", templateContent);

			// Send Email
			sendEmail(receiver.getEmail(), EmailTitlesEnum.getEmailTitle(languageCode, LEAD_NOTIF_TYPE), htmlContent);
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
}
