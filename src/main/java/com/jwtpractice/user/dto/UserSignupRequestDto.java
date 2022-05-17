package com.jwtpractice.user.dto;

import com.jwtpractice.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class UserSignupRequestDto {
	private String email;
	private String password;
	private String name;
	private String address;
	
	
	
	public User toEntity() {
		return new User(email,name, "ROLE_USER", password, address);
	}
	
}
