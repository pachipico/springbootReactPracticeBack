package com.jwtpractice.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jwtpractice.user.domain.User;
import com.jwtpractice.user.dto.UserLoginResponseDto;
import com.jwtpractice.user.dto.UserRequestDto;
import com.jwtpractice.user.dto.UserResponseDto;
import com.jwtpractice.user.dto.UserSignupRequestDto;
import com.jwtpractice.user.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserMapper userMapper;
	
	private final PasswordEncoder encoder;
	
	@Transactional
	@Override
	public List<UserResponseDto> findAllUser() {

		return userMapper.findAllUser().stream().map(u -> new UserResponseDto(u)).collect(Collectors.toList());
	}
	
	@Transactional
	@Override
	public Long save(UserSignupRequestDto userSignupRequestDto) {
		User user = userMapper.findByEmail(userSignupRequestDto.getEmail());
		if(user == null) {
			userSignupRequestDto.setPassword(encoder.encode(userSignupRequestDto.getPassword()));
			userMapper.save(userSignupRequestDto.toEntity());
			return userMapper.findByEmail(userSignupRequestDto.getEmail()).getUserId();
		}

		throw new RuntimeException("이미 사용중인 이메일입니다.");
	}

	@Transactional
	@Override
	public UserLoginResponseDto login(String email, String password) {
		User user = userMapper.findByEmail(email);
		if(!encoder.matches(password, user.getPassword())) {
			throw new RuntimeException("비밀번호가 올바르지 않습니다."); 
		}
		
		return new UserLoginResponseDto(user);
	}

	@Transactional
	@Override
	public List<UserResponseDto> findByName(String name) {
		List<User> users = userMapper.findByName(name);
		if(users.size() == 0) throw new RuntimeException("해당하는 유저가 없습니다.");
		return users.stream().map(u -> new UserResponseDto(u)).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public UserResponseDto findByEmail(String email) {
		User user =  userMapper.findByEmail(email);
		if(user == null) throw new RuntimeException("해당하는 유저가 없습니다.");
		
		return new UserResponseDto(user);
	}
	
	@Transactional
	@Override
	public boolean isEmailValid(String email) {
		return userMapper.isEmailValid(email) == 1  ? true : false;
	}

	@Transactional
	@Override
	public UserResponseDto findByUserId(Long userId) {
		User user =  userMapper.findByUserId(userId);
		if(user == null) throw new RuntimeException("해당하는 유저가 없습니다.");
		
		return new UserResponseDto(user);
	}

	@Transactional
	@Override
	public int deleteUser(Long userId) {
		
		return userMapper.deleteUser(userId);
	}

	@Transactional
	@Override
	public Long modify(Long userId, UserRequestDto userRequestDto) {
		User user = userMapper.findByUserId(userId);
		user.setName(userRequestDto.getName());
		userMapper.modify(user);
		return user.getUserId();
	}

}
