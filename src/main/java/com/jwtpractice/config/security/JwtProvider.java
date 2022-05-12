package com.jwtpractice.config.security;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.jwtpractice.token.dto.TokenDto;
import com.jwtpractice.user.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtProvider {

	@Value("spring.jwt.secret")
	private String secretKey;

	private Long accessTokenValidMillisecond = 60 * 60 * 1000L;
	private Long refreshTokenValidMillisecond = 60 * 60 * 1000L * 24 * 14;

	private final CustomUserDetailsService userDetailsService;
	

	@PostConstruct
	private void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	// accessToken과 refreshToken을 발행해서 TokenDto에 넣어서 같이 리턴.
	public TokenDto createTokenDto(Long userId, List<String> roles) {
		Date now = new Date();
		Map<String, Object> header = new HashMap<String, Object>();
		header.put("typ", "JWT");
		String accessToken = JWT.create().withSubject(String.valueOf(userId))
				.withHeader(header).withIssuedAt(now)
				.withExpiresAt(new Date(now.getTime() + accessTokenValidMillisecond)).withClaim("roles", roles)
				.withClaim("email", userDetailsService.loadUserByUsername(String.valueOf(userId)).getUsername())
				.sign(Algorithm.HMAC512(secretKey));

		String refreshToken = JWT.create().withSubject(String.valueOf(userId)).withClaim("roles", roles)
				.withClaim("email", userDetailsService.loadUserByUsername(String.valueOf(userId)).getUsername())
				.withExpiresAt(new Date(now.getTime() + refreshTokenValidMillisecond))
				.sign(Algorithm.HMAC512(secretKey));
		
		return new TokenDto("Bearer", accessToken, refreshToken, accessTokenValidMillisecond);

	}
	
	// 토큰을 받아서 권한이 있는지를 판단	
	public Authentication getAuthentication(String token) {
		Claim roles = JWT.decode(token).getClaim("roles");
		Claim email = JWT.decode(token).getClaim("email");
		if(roles == null) throw new RuntimeException("권한이 없습니다.");
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(JWT.decode(token).getSubject());
		log.debug("Provider useDetails : {}", userDetails.getUsername());
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}
	
	// 헤더에서 토큰 가져옴.
	public String resolveToken(HttpServletRequest request) {
		return request.getHeader("X-AUTH-TOKEN");
	}
	
	// 토큰이 만료되었는지 판단.
	public boolean validationToken(String token) {
		return JWT.decode(token).getExpiresAt().after(new Date());
	}

}
