package com.flab.jobgo.user.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.flab.jobgo.common.constant.CommonConstant;
import com.flab.jobgo.common.dao.GeneralUserRepository;
import com.flab.jobgo.common.dto.JwtToken;
import com.flab.jobgo.common.entity.EnterpriseUser;
import com.flab.jobgo.common.entity.GeneralUser;
import com.flab.jobgo.common.utils.JwtTokenProvider;
import com.flab.jobgo.user.dto.GeneralUserReqDTO;
import com.flab.jobgo.user.dto.UserLoginRequestDTO;
import com.flab.jobgo.user.exception.DuplicatedUserIdException;
import com.flab.jobgo.user.exception.PasswordNotSameException;
import com.flab.jobgo.user.utils.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GeneralUserService {

	private final GeneralUserRepository userRepository;
	
	private final JwtTokenProvider jwtProvider;
	
	public void userRegist(GeneralUserReqDTO userReqDTO) {
		if(isExistUserId(userReqDTO.getUserId())) {
			throw new DuplicatedUserIdException(userReqDTO.getUserId() + "는 이미 사용중인 Id 입니다.");
		}
		
		GeneralUser enterpriseUser = userReqDTO.transferToGeneralUser();
		userRepository.save(enterpriseUser);
	}

	public boolean isExistUserId(String userId) {
		return userRepository.existsByUserId(userId);
	}
	
	public JwtToken enterpriseUserLogin(UserLoginRequestDTO dto) {
		String userId = dto.getUserId();
		
		GeneralUser user = userRepository.findByUserId(userId);
		
		if(user == null) {
			throw new UsernameNotFoundException(CommonConstant.USER_NOT_FOUND);
		}
		
		if(!PasswordEncoder.isMatch(dto.getPassword(), user.getPw())) {
			throw new PasswordNotSameException(CommonConstant.PASSWORD_NOT_SAME);
		}
		
		List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(CommonConstant.USER_ROLE_GENERAL));
		
		// JwtToken 발행을 위한 Authentication객체 생성, JwtToken발행에 필요한 userId와 권한만 넣어준다
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userId, "", authorities);
		
		return jwtProvider.createJwtToken(authentication);
	}
}
