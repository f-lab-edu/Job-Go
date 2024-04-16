package com.flab.jobgo.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class UserServiceSecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// json으로 로그인을 하기때문에 form형태의 로그인은 비활성화
		http.formLogin(FormLoginConfigurer::disable);
		
		// stateless한 rest api server이기 때문에 csrf는 비활성화
		http.csrf(CsrfConfigurer::disable);
		
		// httpBasic 인증방식을 사용하지 않을것
		http.httpBasic(HttpBasicConfigurer::disable);
		
		return http.build();
	}
}
