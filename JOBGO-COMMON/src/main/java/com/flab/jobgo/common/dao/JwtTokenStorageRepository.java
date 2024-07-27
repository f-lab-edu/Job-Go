package com.flab.jobgo.common.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.flab.jobgo.common.entity.JwtToken;

@Repository
public interface JwtTokenStorageRepository extends CrudRepository<JwtToken, String>{

	Optional<JwtToken> findByAccessToken(String AccessToken);
}
