package de.homedev.thymeleaf.api.exceptions;

public class UsernameNotFoundBackendException extends BackendException {

	private static final long serialVersionUID = 1L;

	public UsernameNotFoundBackendException() {
		super();

	}

	public UsernameNotFoundBackendException(String s, Throwable cause) {
		super(s, cause);

	}

	public UsernameNotFoundBackendException(String s) {
		super(s);
	}

}
