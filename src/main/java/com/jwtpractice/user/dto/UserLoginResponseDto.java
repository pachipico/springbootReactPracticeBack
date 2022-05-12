package com.jwtpractice.user.dto;

import java.util.Arrays;
import java.util.List;

import com.jwtpractice.user.domain.User;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserLoginResponseDto {

	private final Long userId;
	private final List<String> roles;
	private final String createdDate;
	
	public UserLoginResponseDto(User user) {
		this.userId = user.getUserId();
		this.roles = Arrays.asList(user.getRoles().split(","));
		this.createdDate = user.getCreatedDate();
	}
	
	
}
