package com.jwtpractice.user.dto;

import com.jwtpractice.user.domain.User;

import lombok.Getter;

@Getter
public class UserResponseDto {
	private final Long userId;
	private final String email;
	private final String name;
	private final String createdDate;
	private final String modifiedDate;
	
	public UserResponseDto(User user) {
		this.userId = user.getUserId();
		this.email = user.getEmail();
		this.name = user.getName();
		this.createdDate = user.getCreatedDate();
		this.modifiedDate = user.getModifiedDate();
	}
	
}
