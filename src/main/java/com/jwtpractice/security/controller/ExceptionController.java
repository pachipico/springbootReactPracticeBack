package com.jwtpractice.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwtpractice.config.security.handler.CAuthenticationEntrypointException;
import com.jwtpractice.response.result.SingleResult;
import com.jwtpractice.response.service.ResponseService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@RequiredArgsConstructor
@RestController
@RequestMapping("/exception")
public class ExceptionController {
	
	private final ResponseService responseService;
	
	@GetMapping("/accessDenied")
	public SingleResult<String> accessDenied() {
		throw new CAuthenticationEntrypointException();
	}
	
	@GetMapping("/entrypoint")
	public SingleResult<String> entrypoint()	{
		return responseService.getSingleResult("토큰이 없습니다.");
	}
}
