package com.flab.jobgo.common.utils;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.flab.jobgo.common.constant.CommonConstant;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtTokenProvider implements InitializingBean{

	private final Logger log = LoggerFactory.getLogger(getClass());
	private static final String AUTHORITIES_KEY = "auth";
	private final String secretKey;
	private final long tokenValidityInMilliseconds;
	
	private Key decodedKey;
	
	
	public JwtTokenProvider(
			@Value("${jwt.secret}") String secretKey,
			@Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds) {
		log.info("{}, 초기화 시작", this.getClass().getName());
		this.secretKey = secretKey;
		this.tokenValidityInMilliseconds = tokenValidityInSeconds * 1000;
		log.info("{}, 초기화 완료", this.getClass().getName());
	}


	// 빈이 생성되고 의존주입이 끝난 후 secretKey를 Base64로 decode한 key값을 할당
	@Override
	public void afterPropertiesSet() throws Exception {
		byte[] byteSecretKey = Decoders.BASE64.decode(secretKey);
		this.decodedKey = Keys.hmacShaKeyFor(byteSecretKey);
	}
	
	// 로그인 요청시 일치하는 사용자 정보가 존재한다면 권한 정보를 담아 AccessToken 생성
	public String createAccessToken(Authentication authentication) {
	    String authorities = authentication.getAuthorities().stream()
	            .map(GrantedAuthority::getAuthority)
	            .collect(Collectors.joining(","));

	    long now = (new Date()).getTime();
	    Date validity = new Date(now + this.tokenValidityInMilliseconds);

	    return Jwts.builder()
	            .setSubject(authentication.getName()) // 토큰 식별자
	            .claim(AUTHORITIES_KEY, authorities) // 토큰 권한
	            .signWith(decodedKey, SignatureAlgorithm.HS512) // secret을 64byte로 생성하여 HS512 사용
	            .setExpiration(validity) // 토큰 만료시간
	            .setIssuedAt(new Date()) // 토큰 발생시간
	            .compact();
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
	public String resolveToken(HttpServletRequest request) {
		return request.getHeader("Authorization").replace("Bearer ", "");
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

}
