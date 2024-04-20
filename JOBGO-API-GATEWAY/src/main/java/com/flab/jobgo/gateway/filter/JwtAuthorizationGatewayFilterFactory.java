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
import com.flab.jobgo.gateway.constant.GatewayConstant;
import com.flab.jobgo.gateway.exception.NonLoginException;

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
				throw new NonLoginException(GatewayConstant.NON_LOGIN);
			}
			
			try {
				boolean validate = tokenProvider.validateToken(accessToken);
				if(validate) {
					// 토큰 유효
					return chain.filter(exchange);
				}else {
					// 토큰 만료
					boolean existRefreshToken = tokenProvider.existRefreshToken(accessToken);
					
					if(existRefreshToken) {
						// redis에 refreshToken 저장시 refreshToken만료기간과 동일하게 Duration을 설정했기 때문에
						// refreshToken이 존재한다면 refreshToken이 유효함
						
					}
				}
			} catch (Exception e) {
				// 토큰 검증 예외
			}
			
		};
	}

}
