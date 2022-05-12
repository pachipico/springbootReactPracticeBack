package com.jwtpractice.user.dto;

import com.jwtpractice.user.domain.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserSignupRequestDto {
	private String email;
	private String password;
	private String name;
	private String address;
	
	public UserSignupRequestDto(String email, String password, String name) {
		this.email = email;
		this.password = password;
		this.name = name;
	}
	
	public User toEntity() {
		return new User(email,name, "ROLE_USER", password, address);
	}
	
}
