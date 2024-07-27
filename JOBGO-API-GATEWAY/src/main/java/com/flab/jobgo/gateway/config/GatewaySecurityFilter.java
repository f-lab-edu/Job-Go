package com.flab.jobgo.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity.CsrfSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.FormLoginSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.HttpBasicSpec;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.stereotype.Component;

@Component
@EnableWebFluxSecurity
public class GatewaySecurityFilter {

	@Bean
	public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
		http.formLogin(FormLoginSpec::disable);
		
		http.csrf(CsrfSpec::disable);
		
		http.httpBasic(HttpBasicSpec::disable);
		
		return http.build();
	}
}
