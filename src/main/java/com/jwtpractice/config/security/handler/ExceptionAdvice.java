package com.jwtpractice.config.security.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jwtpractice.response.result.CommonResult;
import com.jwtpractice.response.result.SingleResult;
import com.jwtpractice.response.service.ResponseService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {
	
	private final ResponseService service;
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected CommonResult exception (HttpServletRequest request, Exception e) {
		return service.getFailResult();
	}
	
	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected CommonResult nullpoint (HttpServletRequest request, Exception e) {
		return service.getFailResult();
	}
	@ExceptionHandler(CAuthenticationEntrypointException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected SingleResult<String> authentication (HttpServletRequest request, CAuthenticationEntrypointException e) {
		return service.getSingleResult("인증되지 않은 사용자의 요청입니다");
	}

}
