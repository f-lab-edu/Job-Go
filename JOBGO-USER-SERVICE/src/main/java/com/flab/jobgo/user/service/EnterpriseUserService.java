package com.flab.jobgo.user.service;

import org.springframework.stereotype.Service;

import com.flab.jobgo.common.dao.EnterPriseUserRepository;
import com.flab.jobgo.common.entity.EnterpriseUser;
import com.flab.jobgo.user.dto.EnterpriseUserReqDTO;
import com.flab.jobgo.user.exception.DuplicatedUserIdException;

import lombok.RequiredArgsConstructor;

/*
 * EnterpriseUserService는 확장,변경이 거의 없을것으로 판단하여 다형성의 이점이 떨어져 Interface를 상속하지 않음
 * 기업회원 가입,수정,탈퇴,로그인에 대한 책임만을 가지는 SRP을 준수하도록 구현
 */
@Service
@RequiredArgsConstructor
public class EnterpriseUserService{

	private final EnterPriseUserRepository userRepository;
	
	public void userRegist(EnterpriseUserReqDTO userReqDTO) {
		if(isExistUserId(userReqDTO.getUserId())) {
			throw new DuplicatedUserIdException(userReqDTO.getUserId() + "는 이미 사용중인 Id 입니다.");
		}
		
		EnterpriseUser enterpriseUser = userReqDTO.transferToEnterpriseUser();
		userRepository.save(enterpriseUser);
	}

	public boolean isExistUserId(String userId) {
		return userRepository.existsById(userId);
	}

}
