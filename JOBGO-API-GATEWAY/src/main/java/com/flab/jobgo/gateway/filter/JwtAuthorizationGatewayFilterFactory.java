package com.flab.jobgo.gateway.filter;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import com.flab.jobgo.common.utils.JwtTokenProvider;

import lombok.Getter;
import lombok.Setter;

@Component
public class JwtAuthorizationGatewayFilterFactory extends AbstractGatewayFilterFactory<JwtAuthorizationGatewayFilterFactory.Config>{
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Getter
	@Setter
	public static class Config{
		private String message;
	}

	public JwtAuthorizationGatewayFilterFactory() {
		super(Config.class);
	}
	
	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest();
			log.info("JwtAuthorizationGatewayFilter Start : URI -> {}", request.getURI());
			
			String accessToken = tokenProvider.resolveToken(request);
			if(accessToken == null) {
				// 토큰이 존재하지 않으므로 401에러
			}else {
				tokenProvider.validateToken(accessToken);
				if(validate) {
					return chain.filter(exchange);
				}else {
					// accessToken 토큰 만료로 refreshToken가 유효하면 다시 accessToken을 발급한다.
					List<HttpCookie> cookies = request.getCookies().get("refreshToken");
					if(cookies != null && cookies.size() > 0) {
						String refreshToken = cookies.get(0).getValue();
						if(tokenProvider.validateToken(refreshToken)) {
							
						}else {
							
						}
						
					}else {
						// refreshToken이 존재하지 않으면 토큰 만료처리
					}
				}
			}
		};
	}

}
