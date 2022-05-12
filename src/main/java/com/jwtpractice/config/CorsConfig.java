package com.jwtpractice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import lombok.extern.slf4j.Slf4j;

@Configuration
public class CorsConfig {
	
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true); // 내 서버가 응답을 할때 json을 자바스크립트에서 처리를 할 수 있게 설정하는것.
		config.addAllowedHeader("*"); // 모든 헤더에 응답을 허용
		config.addAllowedMethod("*"); // 모든 post, get, put, delete, patch 허용 
		config.addAllowedOriginPattern("*"); // 모든 ip에 응답을 허용
		config.addExposedHeader("X-AUTH-TOKEN");
		source.registerCorsConfiguration("/api/**", config);
		return new CorsFilter(source);
		
	}
}