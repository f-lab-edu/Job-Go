package com.flab.jobgo.enterprise.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.flab.jobgo.common.entity.EnterpriseInfo;
import com.flab.jobgo.enterprise.dao.EnterpriseInfoRepository;
import com.flab.jobgo.enterprise.dao.EnterpriseReviewRepository;
import com.flab.jobgo.enterprise.dto.EnterpriseReviewRequestDTO;
import com.flab.jobgo.enterprise.dto.EnterpriseReviewResponseDTO;
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
	
	public List<EnterpriseReviewResponseDTO> findEnterpriseReviewByEnterpriseId(int enterpriseId, int pageNumber){
		
		// 한 페이지에 10개의 콘텐츠를 등록일시로 내림차순하여 페이징하기 위한 객체
		PageRequest pageRequest = PageRequest.of(pageNumber, 10, Sort.by(Sort.Direction.DESC, "registrationDTM"));
		
		Slice<EnterpriseReview> enterpriseReviewByEnterpriseId = repository.findEnterpriseReviewByEnterpriseId(enterpriseId, pageRequest);
		List<EnterpriseReview> sliceToList = enterpriseReviewByEnterpriseId.getContent();
		
		// 페이징처리로 조회한 Entity를 DTO로 변환
		List<EnterpriseReviewResponseDTO> enterpriseReviewList = sliceToList.stream().map(m -> m.transferToEnterpriseReviewResponseDTO()).collect(Collectors.toList());
		
		return enterpriseReviewList;
	}
	
}
