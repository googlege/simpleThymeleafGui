package de.homedev.thymeleaf.frontend.config;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import de.homedev.thymeleaf.api.model.UserEntity;

@ControllerAdvice
public class UserGlobalAdvice {

	@ModelAttribute(value = "currentUser", binding = false)
	public UserEntity currentUser(final Authentication auth) {
		return Optional.ofNullable(auth).map(a -> (UserEntity) a.getPrincipal()).orElse(null);
	}
}
