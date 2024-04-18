package com.flab.jobgo.common.service;

import lombok.RequiredArgsConstructor;

import java.time.Duration;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {

	private final RedisTemplate<String, Object> template;
	
	public void setValue(String key, Object value) {
		ValueOperations<String, Object> opsForValue = template.opsForValue();
		opsForValue.set(key, value);
	}
	
	public void setValue(String key, Object value, Duration duration) {
		ValueOperations<String, Object> opsForValue = template.opsForValue();
		opsForValue.setIfAbsent(key, value, duration);
	}
	
	public Object getValue(String key) {
		ValueOperations<String, Object> opsForValue = template.opsForValue();
		return opsForValue.get(key);
	}
	
	public void deleteValue(String key) {
		template.delete(key);
	}
}
