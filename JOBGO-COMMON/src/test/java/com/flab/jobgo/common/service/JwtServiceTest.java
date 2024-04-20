package com.flab.jobgo.common.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.flab.jobgo.common.entity.JwtToken;

@SpringBootTest
public class JwtServiceTest {

	@Autowired
	private JwtTokenService service;
	
	private static String accessToken = "accessToken1";
	private static String newAccessToken = "accessToken2";
	private static String refreshToken = "refreshToken1";
	
	@Test
	@DisplayName("로그인시 JwtToken저장")
	public void saveJwtTokenTest() {
		JwtToken jwtToken = JwtToken.builder().accessToken(accessToken).refreshToken(refreshToken).build();
		
		service.saveJwtToken(jwtToken);
		
		JwtToken selectJwtToken = service.selectJwtToken(accessToken);
		
		assertThat(selectJwtToken).isNotNull();
		assertThat(selectJwtToken.getRefreshToken()).isEqualTo(refreshToken);
	}
	
	@Test
	@DisplayName("AccessToken 재발급시 JwtToken update")
	public void updateJwtTokenTest() {
		JwtToken jwtToken = service.selectJwtToken(accessToken);
		
		if(jwtToken != null) {
			jwtToken.setAccessToken(newAccessToken);
			service.saveJwtToken(jwtToken);
		}
		
		JwtToken newJwtToken = service.selectJwtToken(newAccessToken);
		
		assertThat(newJwtToken).isNotNull();
		assertThat(newJwtToken.getRefreshToken()).isEqualTo(refreshToken);
	}
}
