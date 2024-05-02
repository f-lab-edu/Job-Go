package com.flab.jobgo.gateway.filter;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.flab.jobgo.common.entity.JwtToken;
import com.flab.jobgo.common.service.JwtTokenStorageService;
import com.flab.jobgo.common.utils.JwtTokenProvider;
import com.flab.jobgo.gateway.constant.GatewayConstant;
import com.flab.jobgo.gateway.exception.ExpiredTokenException;
import com.flab.jobgo.gateway.exception.JwtValidateException;
import com.flab.jobgo.gateway.exception.NonLoginException;



@Component
public class JwtAuthorizationGatewayFilterFactory extends AbstractGatewayFilterFactory<JwtAuthorizationGatewayFilterFactory.Config>{
	
	private final JwtTokenProvider tokenProvider;
	private final JwtTokenStorageService jwtStorageService;
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	public static class Config{
	}

	public JwtAuthorizationGatewayFilterFactory(JwtTokenProvider tokenProvider,
			JwtTokenStorageService jwtStorageService) {
		super(Config.class);
		this.tokenProvider = tokenProvider;
		this.jwtStorageService = jwtStorageService;
	}
	
	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest();
			ServerHttpResponse response = exchange.getResponse();
			log.info("JwtAuthorizationGatewayFilter Start : URI -> {}", request.getURI());
			String accessToken = tokenProvider.resolveToken(request);
			
			if(accessToken == null) {
				throw new NonLoginException(GatewayConstant.NON_LOGIN);
			}
			
			try {
				boolean validate = tokenProvider.validateToken(accessToken);
				if(validate) {
					// accessToken유효
					return chain.filter(exchange);
				}else {
					// accessToken만료
					JwtToken selectJwtToken = jwtStorageService.selectJwtToken(accessToken);
					
					if(selectJwtToken != null) {
						// redis에 jwtToken 저장시 refreshToken만료기간과 동일하게 Duration을 설정했기 때문에
						// jwtToken이 존재한다면 refreshToken이 유효함
						String refreshToken = selectJwtToken.getRefreshToken();
						
						// refreshToken의 subject와 claim이 accessToken과 동일하기 때문에 동일한 Authentication객체를 생성 
						Authentication authentication = tokenProvider.getAuthentication(refreshToken);
						
						// refreshToken으로 생성한 Authentication으로 jwtToken 재 발행
						JwtToken jwtToken = tokenProvider.createJwtToken(authentication);
						
						// 영속성 객체인 selectJwtToken의 AccessToken을 재발행한 AccessToken으로 변경 후 redis 저장
						selectJwtToken.setAccessToken(jwtToken.getAccessToken());
						
						jwtStorageService.saveJwtToken(jwtToken);
						
						// header에 새로운 AccessToken 저장
						response.getHeaders().set("accessToken", jwtToken.getAccessToken());
						return chain.filter(exchange);
					}else {
						// accessToken만료, refreshToken만료
						throw new ExpiredTokenException(GatewayConstant.EXPIRED_TOKEN);
					}
				}
			} catch (Exception e) {
				// 토큰 검증 예외
				throw new JwtValidateException(GatewayConstant.JWT_VALIDATE);
			}
			
		};
	}
	

}
