package de.homedev.thymeleaf.api.exceptions;

import java.rmi.RemoteException;

import de.homedev.thymeleaf.api.util.AppUtil;

public class BackendException extends RemoteException {
	private static final long serialVersionUID = 1L;

	public BackendException() {
		super();

	}

	public BackendException(Throwable cause) {
		super(AppUtil.throwableToString(cause));
	}

	public BackendException(String s, Throwable cause) {
		super(s + "\r\n" + AppUtil.throwableToString(cause));

	}

	public BackendException(String s) {
		super(s);
	}

}
