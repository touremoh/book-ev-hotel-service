package com.bookevhotel.core.enums;

import lombok.Getter;

@Getter
public enum EmailTitlesEnum {

	EN_SUBJECT("en", "Action Required: Activate Your Account with Book EV Hotel (Code Inside)"),
	FR_SUBJECT("fr", "Action requise : Activez votre compte avec Book EV Hotel (Code à l'intérieur)"),
	DE_SUBJECT("de", "Aktion erforderlich: Aktivieren Sie Ihr Konto bei Book EV Hotel (Code enthalten)"),
	IT_SUBJECT("it", "Azione richiesta: Attiva il tuo account con Book EV Hotel (Codice all'interno)"),
	ES_SUBJECT("es", "Acción requerida: Activa tu cuenta con Book EV Hotel (Código dentro)"),
	PT_SUBJECT("pt", "Ação necessária: Ative sua conta com Book EV Hotel (Código dentro)");

	EmailTitlesEnum(String languageCode, String emailTitle) {
		this.languageCode = languageCode;
		this.emailTitle = emailTitle;
	}

	public static String getEmailTitle(String languageCode) {
		for (EmailTitlesEnum e : EmailTitlesEnum.values()) {
			if (e.getLanguageCode().equals(languageCode)) {
				return e.getEmailTitle();
			}
		}
		return EN_SUBJECT.getEmailTitle();
	}

	private final String languageCode;
	private final String emailTitle;
}
