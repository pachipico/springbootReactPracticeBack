package com.jwtpractice.config.security.kakaologin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jwtpractice.token.service.KakaoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth/kakao")
public class KakaoLogin {
	
	private final KakaoService service;
	
	@GetMapping("/redirect")
	public void redirect(@RequestParam String code) {
		log.debug("@@@@@@@@  {}",code);
		service.getKakaoTokenInfo(code);
	}
	
}
