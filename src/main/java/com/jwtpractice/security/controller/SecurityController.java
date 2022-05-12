package com.jwtpractice.security.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jwtpractice.response.result.SingleResult;
import com.jwtpractice.response.service.ResponseService;
import com.jwtpractice.security.service.SecurityService;
import com.jwtpractice.token.dto.LoginDto;
import com.jwtpractice.token.dto.TokenDto;
import com.jwtpractice.token.dto.TokenRequestDto;
import com.jwtpractice.user.dto.UserLoginRequestDto;
import com.jwtpractice.user.dto.UserResponseDto;
import com.jwtpractice.user.dto.UserSignupRequestDto;
import com.jwtpractice.user.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class SecurityController {
	
	private final UserService userService;
	
	private final SecurityService securityService;
	
	private final ResponseService responseService;

	// singleREsult 로 감싸면 500에러 떨어짐;;; 왜그런지 모르겠음.
	
	@PostMapping("/login")
	public SingleResult<TokenDto> login (@RequestBody UserLoginRequestDto userLoginRequestDto){
		TokenDto tokenDto = securityService.login(userLoginRequestDto);
//		LoginDto loginDto = new LoginDto(userLoginRequestDto.getEmail(), tokenDto.getAccessToken(), tokenDto.getAccessToken());
		log.debug("Controller ===> token : {}", tokenDto);
		return responseService.getSingleResult(tokenDto);
		
	}
	
	@PostMapping("/emailchk")
	public SingleResult<Boolean> validEmailChk(@RequestBody UserSignupRequestDto userSignupRequestDto){
		
		return responseService.getSingleResult(userService.isEmailValid(userSignupRequestDto.getEmail()));
	}
	
	
	@PostMapping("/signup")
	public SingleResult<Long> signup (@RequestBody UserSignupRequestDto userSignupRequestDto) {
		Long signUpId = securityService.signup(userSignupRequestDto);
		log.debug("Controller ===> signUpId: {}", signUpId);
		return responseService.getSingleResult(signUpId);
		
	}
	
	@PostMapping("/reissue")
	public SingleResult<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto){
		TokenDto tokenDto = securityService.reissue(tokenRequestDto);
		log.debug("tokenDto >>>>>> {}", tokenDto);
		return responseService.getSingleResult(tokenDto);
	}
}
