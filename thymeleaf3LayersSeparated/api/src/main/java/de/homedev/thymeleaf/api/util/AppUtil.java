package de.homedev.thymeleaf.api.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public final class AppUtil {
	private AppUtil() {
	}

	private static final int BCRYPT_ROUNDS = 12;
	public static final String EMPTY = "";
	public static final SimpleDateFormat dfFull = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

	public static PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder(BCRYPT_ROUNDS);
	}

	public static void main(String[] args) {
		String rawPassword = "user";
		String encodedPassword = "$2a$12$kSx0dpfTkuFzNtxOR85GHOITwRRPg5oJa3k91zmBL5KkgdxwONKVG";
		PasswordEncoder pe = getPasswordEncoder();
		System.out.println(pe.encode(rawPassword));
		System.out.println("match:" + pe.matches(rawPassword, encodedPassword));
	}

	public static String toString(final Date date) {
		return date != null ? dfFull.format(date) : "";
	}

	public static String toString(final ZonedDateTime date) {
		if (date != null) {
			return toString(GregorianCalendar.from(date).getTime());
		}
		return EMPTY;
	}

	public static String throwableToString(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		return sw.toString(); // stack trace as a string
	}
}
