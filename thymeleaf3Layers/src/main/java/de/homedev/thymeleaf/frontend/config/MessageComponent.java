package de.homedev.thymeleaf.frontend.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageComponent {

	private final MessageSource messageSource;

	@Autowired
	public MessageComponent(final MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public String getMessage(final String code) {
		final Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(code, null, code, locale);
	}

	public String getMessage(final String code, final Object... args) {
		final Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(code, args, code, locale);
	}

}
