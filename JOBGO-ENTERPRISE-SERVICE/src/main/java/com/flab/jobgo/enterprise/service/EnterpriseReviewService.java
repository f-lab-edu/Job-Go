package com.flab.jobgo.enterprise.service;

import org.springframework.stereotype.Service;

import com.flab.jobgo.common.entity.EnterpriseInfo;
import com.flab.jobgo.enterprise.dao.EnterpriseInfoRepository;
import com.flab.jobgo.enterprise.dao.EnterpriseReviewRepository;
import com.flab.jobgo.enterprise.dto.EnterpriseReviewRequestDTO;
import com.flab.jobgo.enterprise.entity.EnterpriseReview;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnterpriseReviewService {

	private final EnterpriseReviewRepository repository;
	
	private final EnterpriseInfoRepository enterpriseInfoRepository;
	
	public void enterpriseReviewRegist(EnterpriseReviewRequestDTO enterpriseReviewReqDTO, int enterpriseId) {
		EnterpriseInfo enterpriseInfo = enterpriseInfoRepository.findById(enterpriseId).orElseThrow();
		
		EnterpriseReview enterpriseReview = enterpriseReviewReqDTO.transferToEnterpriseReview();
		
		enterpriseReview.addEnterpriseInfo(enterpriseInfo);
		
		repository.save(enterpriseReview);
	}
}
