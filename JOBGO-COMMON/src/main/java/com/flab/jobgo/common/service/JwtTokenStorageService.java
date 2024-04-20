package com.flab.jobgo.common.service;

import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Service;

import com.flab.jobgo.common.dao.JwtTokenStorageRepository;
import com.flab.jobgo.common.entity.JwtToken;

@Service
@RequiredArgsConstructor
public class JwtTokenStorageService {

	private final JwtTokenStorageRepository repository;
	
	public void saveJwtToken(JwtToken token){
		repository.save(token);
	}
	
	public void removeJwtToken(String accessToken) {
		repository.findByAccessToken(accessToken)
			.ifPresent(token -> repository.delete(token));
	}
	
	public JwtToken selectJwtToken(String accessToken) {
		return repository.findByAccessToken(accessToken).orElse(null);
	}
}
