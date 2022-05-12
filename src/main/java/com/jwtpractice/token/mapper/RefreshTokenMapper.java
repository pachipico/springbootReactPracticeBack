package com.jwtpractice.token.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.jwtpractice.token.domain.RefreshToken;

@Mapper
public interface RefreshTokenMapper {

	int save(RefreshToken token);
	RefreshToken findByKey(Long key);
	void updateToken(RefreshToken token);
}
