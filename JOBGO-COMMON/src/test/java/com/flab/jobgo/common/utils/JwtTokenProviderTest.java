package com.flab.jobgo.common.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;

@SpringBootTest
public class JwtTokenProviderTest {

	@Autowired
	private JwtTokenProvider jwtProvider;
	
	private String accessToken;
	
	@BeforeEach
	public void beforeEach() {
		TestingAuthenticationToken authenticationToken = new TestingAuthenticationToken("userA", "", "ROL_GENERAL_USER");
		
		this.accessToken = jwtProvider.createAccessToken(authenticationToken);
	}
	
	@Test
	public void validateTokenTest() {
		boolean validate = jwtProvider.validateToken(accessToken);
		
		if(validate) {
			Authentication authentication = jwtProvider.getAuthentication(accessToken);
			assertThat(authentication.getPrincipal()).isEqualTo("userA");
			assertThat(authentication.getAuthorities().size()).isEqualTo(1);
		}
	}
}
