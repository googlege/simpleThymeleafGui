package de.homedev.thymeleaf.frontend.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserAuthService extends UserDetailsService {
	public static final String SERVICE_NAME = "IUserAuthService";

}
