package com.jwtpractice.user.dto;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@ToString
public class UserLoginRequestDto {
	private String email;
	private String password;
	
	
}
