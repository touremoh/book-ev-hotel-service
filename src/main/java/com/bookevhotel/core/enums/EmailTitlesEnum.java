package com.bookevhotel.core.enums;

import com.bookevhotel.core.exception.BookEVHotelException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum EmailTitlesEnum {

	EN_ACCOUNT_NOTIFICATION_SUBJECT("en","ACC", "Action Required: Activate Your Account with Book EV Hotel (Code Inside)"),
	FR_ACCOUNT_NOTIFICATION_SUBJECT("fr","ACC", "Action requise : Activez votre compte avec Book EV Hotel (Code à l'intérieur)"),
	DE_ACCOUNT_NOTIFICATION_SUBJECT("de","ACC", "Aktion erforderlich: Aktivieren Sie Ihr Konto bei Book EV Hotel (Code enthalten)"),
	IT_ACCOUNT_NOTIFICATION_SUBJECT("it","ACC", "Azione richiesta: Attiva il tuo account con Book EV Hotel (Codice all'interno)"),
	ES_ACCOUNT_NOTIFICATION_SUBJECT("es","ACC", "Acción requerida: Activa tu cuenta con Book EV Hotel (Código dentro)"),
	PT_ACCOUNT_NOTIFICATION_SUBJECT("pt","ACC", "Ação necessária: Ative sua conta com Book EV Hotel (Código dentro)"),

	EN_LEAD_NOTIFICATION_SUBJECT("en", "LEAD", "Unlock Your Special Offer with Book EV Hotel (Code Inside)"),
	FR_LEAD_NOTIFICATION_SUBJECT("fr", "LEAD", "Offre spéciale : Découvrez votre code avec Book EV Hotel (Code à l'intérieur)"),
	DE_LEAD_NOTIFICATION_SUBJECT("de", "LEAD", "Exklusives Angebot: Entdecken Sie Ihren Code mit Book EV Hotel (Code enthalten)"),
	IT_LEAD_NOTIFICATION_SUBJECT("it", "LEAD", "Offerta esclusiva: Scopri il tuo codice con Book EV Hotel (Codice all'interno)"),
	ES_LEAD_NOTIFICATION_SUBJECT("es", "LEAD", "Oferta exclusiva: Descubre tu código con Book EV Hotel (Código dentro)"),
	PT_LEAD_NOTIFICATION_SUBJECT("pt", "LEAD", "Oferta especial: Descubra seu código com Book EV Hotel (Código dentro)");


	EmailTitlesEnum(String languageCode, String notificationType, String emailTitle) {
		this.languageCode = languageCode;
		this.emailTitle = emailTitle;
		this.notificationType = notificationType;
	}

	public static String getEmailTitle(String languageCode, String notificationType) throws BookEVHotelException {
		for (EmailTitlesEnum e : EmailTitlesEnum.values()) {
			if (e.getLanguageCode().equals(languageCode) && notificationType.equals(e.getNotificationType())) {
				return e.getEmailTitle();
			}
		}
		throw new BookEVHotelException(
			"Unknown language code "+languageCode,
			HttpStatus.INTERNAL_SERVER_ERROR.value(),
			HttpStatus.INTERNAL_SERVER_ERROR
		);
	}

	private final String languageCode;
	private final String emailTitle;
	private final String notificationType;
}
