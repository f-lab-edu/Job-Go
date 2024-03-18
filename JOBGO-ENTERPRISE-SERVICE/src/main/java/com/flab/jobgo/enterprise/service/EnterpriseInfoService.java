package com.flab.jobgo.enterprise.service;

import org.springframework.stereotype.Service;

import com.flab.jobgo.common.dto.EnterpriseInfoResponseDTO;
import com.flab.jobgo.common.entity.EnterpriseInfo;
import com.flab.jobgo.enterprise.dao.EnterpriseInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnterpriseInfoService {

	private final EnterpriseInfoRepository repository;
	
	public EnterpriseInfoResponseDTO findEnterpriseInfoByUserId(String userId) {
		EnterpriseInfo enterpriseInfo = repository.findByUserId(userId);
		EnterpriseInfoResponseDTO enterpriseInfoResponseDTO = enterpriseInfo.transferToEnterpriseInfoResponseDTO();
		
		return enterpriseInfoResponseDTO;
	}
}
