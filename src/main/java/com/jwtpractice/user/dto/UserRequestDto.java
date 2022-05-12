package com.jwtpractice.user.dto;

import com.jwtpractice.user.domain.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDto {
	
	private String email;
	private String name;
	
	
	public UserRequestDto(String email, String name) {
		
		this.email = email;
		this.name = name;
	}
	
	public User toEntity() {
		return new User(null, email, name, null, null, null, null, null);
	}
	
}
