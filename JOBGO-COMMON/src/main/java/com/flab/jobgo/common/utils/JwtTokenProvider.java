package com.flab.jobgo.common.utils;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.flab.jobgo.common.entity.JwtToken;
import com.flab.jobgo.common.service.JwtTokenStorageService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtTokenProvider implements InitializingBean{

	private final Logger log = LoggerFactory.getLogger(getClass());
	private static final String AUTHORITIES_KEY = "auth";
	private final String secretKey;
	private final long accessTokenValidityInMilliseconds;
	private final long refreshTokenValidityInMilliseconds;
	
	private Key decodedKey;
	
	private final JwtTokenStorageService jwtTokenStorageService;
	
	
	public JwtTokenProvider(
			@Value("${jwt.secret}") String secretKey,
			@Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds,
			JwtTokenStorageService jwtTokenStorageService) {
		log.info("{}, 초기화 시작", this.getClass().getName());
		this.secretKey = secretKey;
		this.accessTokenValidityInMilliseconds = tokenValidityInSeconds * 1000; // 1시간 
		this.refreshTokenValidityInMilliseconds = tokenValidityInSeconds * 1000 * 24 * 7; // 1주일
		this.jwtTokenStorageService = jwtTokenStorageService;
		log.info("{}, 초기화 완료", this.getClass().getName());
	}


	// 빈이 생성되고 의존주입이 끝난 후 secretKey를 Base64로 decode한 key값을 할당
	@Override
	public void afterPropertiesSet() throws Exception {
		byte[] byteSecretKey = Decoders.BASE64.decode(secretKey);
		this.decodedKey = Keys.hmacShaKeyFor(byteSecretKey);
	}
	
	// 로그인 성공시 JwtToken 발행
	public JwtToken createJwtToken(Authentication authentication) {
	    String authorities = authentication.getAuthorities().stream()
	            .map(GrantedAuthority::getAuthority)
	            .collect(Collectors.joining(","));

	    long now = (new Date()).getTime();

	    // 사용자 인증에 필요한 AccessToken 생성
	    String accessToken = Jwts.builder()
	            .setSubject(authentication.getName()) // 토큰 식별자
	            .claim(AUTHORITIES_KEY, authorities) // 토큰 권한
	            .signWith(decodedKey, SignatureAlgorithm.HS512) // secret을 64byte로 생성하여 HS512 사용
	            .setExpiration(new Date(now + this.accessTokenValidityInMilliseconds)) // 토큰 만료시간(1시간)
	            .setIssuedAt(new Date()) // 토큰 발행시간
	            .compact();
	    
	    // AccessToken이 만료되었을 경우 재발급을 위한 RefreshToken 생성
	    String refreshToken = Jwts.builder()
	    		.setSubject(authentication.getName()) // 토큰 식별자
	            .claim(AUTHORITIES_KEY, authorities) // 토큰 권한
		        .signWith(decodedKey, SignatureAlgorithm.HS512) // secret을 64byte로 생성하여 HS512 사용
		        .setExpiration(new Date(now + this.refreshTokenValidityInMilliseconds)) // 토큰 만료시간(1주일)
		        .setIssuedAt(new Date()) // 토큰 발행시간
		        .compact();
	    
	    return JwtToken.builder()
	    		.accessToken(accessToken)
	    		.refreshToken(refreshToken)
	    		.build();
	}
	
	// Security Filter에서 인증과정시 인증객체를 생성
	public Authentication getAuthentication(String token) {
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(decodedKey)
				.build()
				.parseClaimsJws(token)
				.getBody();
		
		List<SimpleGrantedAuthority> authorities = 
				Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		
		return new UsernamePasswordAuthenticationToken(claims.getSubject(), "", authorities);
	}
	
	// Request Header에서 토큰 조회
	public String resolveToken(ServerHttpRequest request) {
		List<String> AuthorizationHeader = request.getHeaders().get("Authorization");
		if(AuthorizationHeader != null && AuthorizationHeader.size() > 0) {
			String bearerToken = AuthorizationHeader.get(0);
			if(StringUtils.isNotBlank(bearerToken) && StringUtils.startsWith(bearerToken, "Bearer ")) {			
				return bearerToken.replace("Bearer ", "");
			}
		}
		return null;
	}
	
	// JWT Token 검증
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(decodedKey).build().parseClaimsJws(token);
			return true;
		}catch(ExpiredJwtException e) {
			log.info("JWT Token Expired", e);
			return false;
		}catch(JwtException e) {
			log.info("JWT Token validation error", e);
			throw e;
		}
	}
	
	public boolean existRefreshToken(String accessToken) {
		return jwtTokenStorageService.selectJwtToken(accessToken) != null;
	}

}
