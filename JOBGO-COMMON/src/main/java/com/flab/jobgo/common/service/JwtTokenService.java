package com.flab.jobgo.common.service;

import lombok.RequiredArgsConstructor;

import java.time.Duration;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.flab.jobgo.common.dao.JwtTokenRepository;
import com.flab.jobgo.common.entity.JwtToken;

@Service
@RequiredArgsConstructor
public class JwtTokenService {

	private final JwtTokenRepository repository;
	
	public void saveJwtToken(JwtToken token) {
		repository.save(token);
	}
	
	public void removeJwtToken(String accessToken) {
		repository.findByAccessToken(accessToken).ifPresent(token -> repository.delete(token));
	}
	
	public JwtToken selectJwtToken(String accessToken) {
		return repository.findByAccessToken(accessToken).orElse(null);
	}
}
