package com.jwtpractice.token.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TokenDto {

	private String grantType;
	private String accessToken;
	private String refreshToken;
	private Long accessTokenValidMillisecond;
	public TokenDto(String grantType, String accessToken, String refreshToken, Long accessTokenValidMillisecond) {
		this.grantType = grantType;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.accessTokenValidMillisecond = accessTokenValidMillisecond;
	}
	
}
