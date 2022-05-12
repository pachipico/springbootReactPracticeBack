package com.jwtpractice.token.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class LoginDto {
	private String email;
	private String accessToken;
	private String refreshToken;
}
