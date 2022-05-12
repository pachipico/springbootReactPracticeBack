package com.jwtpractice.config.security.handler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CAuthenticationEntrypointException extends RuntimeException {
	public CAuthenticationEntrypointException() {
		super();
	}
	public CAuthenticationEntrypointException(String message) {
		super(message);
	}
	public CAuthenticationEntrypointException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
