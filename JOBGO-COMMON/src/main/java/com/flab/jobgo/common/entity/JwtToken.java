package com.flab.jobgo.common.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@RedisHash(value = "jwtToken", timeToLive = 60 * 60 * 24 * 7) // refreshToken의 유효기간인 7일로 redis에 jwtToken 저장
public class JwtToken {

	
	@Id
	private String id;
	
	@Indexed
	private String accessToken;
	
	private String refreshToken;

	@Builder
	public JwtToken(String accessToken, String refreshToken) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
	
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}
