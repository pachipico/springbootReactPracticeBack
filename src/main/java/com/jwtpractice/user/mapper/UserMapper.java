package com.jwtpractice.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jwtpractice.user.domain.User;
import com.jwtpractice.user.dto.UserLoginResponseDto;

@Mapper
public interface UserMapper {
	int save(User user);
	List<User> findByName(String name);
	User findByEmail(String email);
	User findLastSaved();
	int isEmailValid(String email);
	List<User> findAllUser();
	User findByUserId(Long userId);
	int modify(User user);
	int deleteUser(Long userId);
	UserLoginResponseDto login(String email, String password);
}
