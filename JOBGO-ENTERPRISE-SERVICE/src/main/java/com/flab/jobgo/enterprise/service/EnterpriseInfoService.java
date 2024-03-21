package com.flab.jobgo.enterprise.service;


import java.util.Optional;

import org.springframework.stereotype.Service;

import com.flab.jobgo.common.dto.EnterpriseInfoResponseDTO;
import com.flab.jobgo.common.entity.EnterpriseInfo;
import com.flab.jobgo.enterprise.dao.EnterpriseInfoRepository;
import com.flab.jobgo.enterprise.dto.EnterpriseInfoRequestDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnterpriseInfoService {

	private final EnterpriseInfoRepository repository;
	
	// 기업 회원 - 본인 기업 조회
	public EnterpriseInfoResponseDTO findEnterpriseInfoByUserId(String userId) {
		EnterpriseInfo enterpriseInfo = repository.findByUserId(userId);
		EnterpriseInfoResponseDTO enterpriseInfoResponseDTO = enterpriseInfo.transferToEnterpriseInfoResponseDTO();
		
		return enterpriseInfoResponseDTO;
	}
	
	// 일반 회원 - 선택한 기업 조회
	public EnterpriseInfoResponseDTO findEnterpriseInfoByPK(Integer enterpriseId) {
		EnterpriseInfo enterpriseInfo = repository.findById(enterpriseId).get();
		EnterpriseInfoResponseDTO enterpriseInfoResponseDTO = enterpriseInfo.transferToEnterpriseInfoResponseDTO();
		
		return enterpriseInfoResponseDTO;
	}
	
	public void updateEnterpriseInfo(EnterpriseInfoRequestDTO enterpriseInfoReqDTO) {
		EnterpriseInfo enterpriseInfo = repository.findById(enterpriseInfoReqDTO.getEnterpriseId()).orElseThrow();
		enterpriseInfo = enterpriseInfoReqDTO.transferToEnterpriseInfo(enterpriseInfo);
		
		
		repository.save(enterpriseInfo);
	}
}
