package com.flab.jobgo.user.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.flab.jobgo.common.constant.CommonConstant;
import com.flab.jobgo.common.dao.EnterPriseUserRepository;
import com.flab.jobgo.common.entity.JwtToken;
import com.flab.jobgo.common.entity.EnterpriseUser;
import com.flab.jobgo.common.utils.JwtTokenProvider;
import com.flab.jobgo.user.dto.EnterpriseUserReqDTO;
import com.flab.jobgo.user.dto.UserLoginRequestDTO;
import com.flab.jobgo.user.exception.DuplicatedUserIdException;
import com.flab.jobgo.user.exception.PasswordNotSameException;
import com.flab.jobgo.user.utils.PasswordEncoder;

import lombok.RequiredArgsConstructor;

/*
 * EnterpriseUserService는 확장,변경이 거의 없을것으로 판단하여 다형성의 이점이 떨어져 Interface를 상속하지 않음
 * 기업회원 가입,수정,탈퇴,로그인에 대한 책임만을 가지는 SRP을 준수하도록 구현
 */
@Service
@RequiredArgsConstructor
public class EnterpriseUserService{

	private final EnterPriseUserRepository userRepository;
	
	private final JwtTokenProvider jwtProvider;
	
	public void userRegist(EnterpriseUserReqDTO userReqDTO) {
		if(isExistUserId(userReqDTO.getUserId())) {
			throw new DuplicatedUserIdException(userReqDTO.getUserId() + "는 이미 사용중인 Id 입니다.");
		}
		
		EnterpriseUser enterpriseUser = userReqDTO.transferToEnterpriseUser();
		userRepository.save(enterpriseUser);
	}

	public boolean isExistUserId(String userId) {
		return userRepository.existsByUserId(userId);
	}
	
	public JwtToken enterpriseUserLogin(UserLoginRequestDTO dto) {
		String userId = dto.getUserId();
		
		EnterpriseUser user = userRepository.findByUserId(userId);
		
		if(user == null) {
			throw new UsernameNotFoundException(CommonConstant.USER_NOT_FOUND);
		}
		
		if(!PasswordEncoder.isMatch(dto.getPassword(), user.getPw())) {
			throw new PasswordNotSameException(CommonConstant.PASSWORD_NOT_SAME);
		}
		
		List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(CommonConstant.USER_ROLE_ENTERPRISE));
		
		// JwtToken 발행을 위한 Authentication객체 생성, JwtToken발행에 필요한 userId와 권한만 넣어준다
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userId, "", authorities);
		
		return jwtProvider.createJwtToken(authentication);
	}

}
