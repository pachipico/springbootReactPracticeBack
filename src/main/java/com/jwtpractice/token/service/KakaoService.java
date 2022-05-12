package com.jwtpractice.token.service;



import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.jwtpractice.token.dto.KakaoOAuth;

import jdk.internal.org.jline.utils.Log;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoService {
	
	private final RestTemplate restTemplate;
	
	

	public KakaoOAuth getKakaoTokenInfo(String code) {
		Gson gson = new Gson();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("gramd_type" ,"authorization_code");
		params.add("client_id", "8a8818131c4dd752e56495002578a2c3");
		params.add("redirect_uri", "http://localhost:8080/oauth/kakao/redirect");
		params.add("code", code);
		String requestUri = "https://kauth.kakao.com/oauth/token";
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String,String>>(params, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(requestUri, request, String.class);
		log.debug("kakao ==={}", response);
		if(response.getStatusCode() == HttpStatus.OK) {
			return gson.fromJson(response.getBody(), KakaoOAuth.class);
		} 
		throw new RuntimeException("카카오 오류났어용");
	}
}
