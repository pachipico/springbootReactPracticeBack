package com.jwtpractice.user.service;

import java.util.List;

import com.jwtpractice.user.dto.UserLoginResponseDto;
import com.jwtpractice.user.dto.UserRequestDto;
import com.jwtpractice.user.dto.UserResponseDto;
import com.jwtpractice.user.dto.UserSignupRequestDto;



public interface UserService {
	List<UserResponseDto> findAllUser();
	Long save(UserSignupRequestDto userSignupRequestDto);
	UserLoginResponseDto login(String email, String password);
	List<UserResponseDto> findByName(String name);
	UserResponseDto findByEmail(String email);
	UserResponseDto findByUserId(Long userId);
	boolean isEmailValid(String email);
	int deleteUser(Long userId);
	Long modify(Long userId, UserRequestDto userRequestDto);
}
