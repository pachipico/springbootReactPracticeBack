package com.jwtpractice.token.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class RefreshToken {
	
	private String id;
	
	private Long key;
	
	private String token;
	
	private String CreatedDate;
	
	private String ModifiedDate;

	public RefreshToken updateToken(String token) {
		this.token = token;
		return this;
	}
	
	public RefreshToken(Long key, String token) {
		this.key = key;
		this.token = token;
	}
	
	
}
