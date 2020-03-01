package com.users.messages.management.helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationHelper {
	public static Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
}
