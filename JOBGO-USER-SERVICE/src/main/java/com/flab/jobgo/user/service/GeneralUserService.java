package com.flab.jobgo.user.service;

import org.springframework.stereotype.Service;

import com.flab.jobgo.common.dao.GeneralUserRepository;
import com.flab.jobgo.common.entity.GeneralUser;
import com.flab.jobgo.user.dto.GeneralUserReqDTO;
import com.flab.jobgo.user.exception.DuplicatedUserIdException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GeneralUserService {

	private final GeneralUserRepository userRepository;
	
	public void userRegist(GeneralUserReqDTO userReqDTO) {
		if(isExistUserId(userReqDTO.getUserId())) {
			throw new DuplicatedUserIdException(userReqDTO.getUserId() + "는 이미 사용중인 Id 입니다.");
		}
		
		GeneralUser enterpriseUser = userReqDTO.transferToGeneralUser();
		userRepository.save(enterpriseUser);
	}

	public boolean isExistUserId(String userId) {
		return userRepository.existsById(userId);
	}
}
