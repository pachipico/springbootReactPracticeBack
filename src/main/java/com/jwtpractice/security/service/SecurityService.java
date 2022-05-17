package com.jwtpractice.security.service;

import java.util.Arrays;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jwtpractice.config.security.JwtProvider;
import com.jwtpractice.token.domain.RefreshToken;
import com.jwtpractice.token.dto.TokenDto;
import com.jwtpractice.token.dto.TokenRequestDto;
import com.jwtpractice.token.mapper.RefreshTokenMapper;
import com.jwtpractice.user.domain.User;
import com.jwtpractice.user.dto.UserLoginRequestDto;
import com.jwtpractice.user.dto.UserSignupRequestDto;
import com.jwtpractice.user.mapper.UserMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SecurityService {

	private final UserMapper userMapper;
	private final PasswordEncoder encoder;
	private final RefreshTokenMapper tokenMapper;
	private final JwtProvider jwtProvider;
	
	@Transactional
	public TokenDto login(UserLoginRequestDto userLoginRequestDto) {
		String email = userLoginRequestDto.getEmail();
		String password = userLoginRequestDto.getPassword();
		User user = userMapper.findByEmail(email);
		log.debug("user: >>>>>>{}", user);
		if(user == null) throw new RuntimeException("이메일을 확인해주세요.");
		
		if(!encoder.matches(password, user.getPassword())) throw new RuntimeException("비밀번호가 일치하지 않습니다."); 
		
		TokenDto tokenDto =  jwtProvider.createTokenDto(user.getUserId(), Arrays.asList(user.getRoles().split(",")));
		
		RefreshToken refreshToken = new RefreshToken(user.getUserId(), tokenDto.getRefreshToken());
		log.debug("login refreshToken : {}", refreshToken);
		if(tokenMapper.findByKey(user.getUserId()) != null) {
			tokenMapper.updateToken(refreshToken);
		} else {
			
		tokenMapper.save(refreshToken);
		}
		return tokenDto;
	}
	
	@Transactional
	public Long signup(UserSignupRequestDto userSignupRequestDto) {
		if(userMapper.findByEmail(userSignupRequestDto.getEmail())!= null) throw new RuntimeException("이미 사용중인 이메일 입니다.");
		userSignupRequestDto.setPassword(encoder.encode(userSignupRequestDto.getPassword()));
		log.debug("userSignupRequestDto>>>>>>> {}", userSignupRequestDto);
		userMapper.save(userSignupRequestDto.toEntity());
		User user = userMapper.findLastSaved();
		log.debug("findLastSaved >>>>{}", user);
		return user.getUserId();
	}
	
	@Transactional
	public TokenDto reissue(TokenRequestDto tokenRequestDto) {
		
		if(!jwtProvider.validationToken(tokenRequestDto.getRefreshToken())) throw new RuntimeException("유효하지 않은 토큰입니다.");
		log.debug("reissue token : {}", tokenRequestDto);
		String accessToken = tokenRequestDto.getAccessToken();
		Authentication authentication = jwtProvider.getAuthentication(accessToken);
		
		User user = userMapper.findByEmail(authentication.getName());
		RefreshToken refreshToken = tokenMapper.findByKey(user.getUserId());
		log.debug("refresh token reissue>>{}", refreshToken);
		log.debug("refresh token reissue>>{}", tokenRequestDto.getRefreshToken());
		if(!refreshToken.getToken().equals(tokenRequestDto.getRefreshToken())) throw new RuntimeException("토큰의 정보가 다릅니다.");
		TokenDto tokenDto = jwtProvider.createTokenDto(user.getUserId(), Arrays.asList(user.getRoles()));
		log.debug("tokenDto>>{}", tokenDto.getAccessToken());
		RefreshToken newRefreshToken = refreshToken.updateToken(tokenDto.getRefreshToken());
		tokenMapper.updateToken(newRefreshToken);
		return tokenDto;
	}
}
