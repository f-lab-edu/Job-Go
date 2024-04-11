package com.flab.jobgo.enterprise.service;

import java.math.BigDecimal;
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
import com.flab.jobgo.enterprise.dto.EnterpriseReviewAverageRatingDTO;
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
	
	public EnterpriseReviewAverageRatingDTO findEnterpriseAverageReviewRatingByEnterpriseId(int enterpriseId) {
		 List<EnterpriseReview> enterpriseReviewByEnterpriseId = repository.findEnterpriseReviewByEnterpriseId(enterpriseId);
		 
		 EnterpriseReviewAverageRatingDTO reviewAverageRatingDTO = EnterpriseReviewAverageRatingDTO.builder()
																 	.totalRating(BigDecimal.ZERO)
																 	.benefitSalraryRating(BigDecimal.ZERO)
																 	.cultureRating(BigDecimal.ZERO)
																 	.potentialRating(BigDecimal.ZERO)
																 	.workLifeRating(BigDecimal.ZERO)
																 	.reviewCount(BigDecimal.ZERO)
																 	.build();
		 
		 if(enterpriseReviewByEnterpriseId != null && enterpriseReviewByEnterpriseId.size() > 0) {
			 List<EnterpriseReviewAverageRatingDTO> enterpriseReviewList = enterpriseReviewByEnterpriseId
					 														.stream()
					 														.map(m -> m.transferToEnterpriseReviewAverageRatingDTO())
					 														.collect(Collectors.toList());
			 
			 // 모든 리뷰의 각 평점의 합계 계산
			 EnterpriseReviewAverageRatingDTO sumReviewAverageRating = enterpriseReviewList
					 													.stream()
					 													.reduce(reviewAverageRatingDTO, (x,y) -> x.sumEnterpriseReviewRating(y));
			 
			 // 평점의 합계를 리뷰 갯수로 나누어 평균 평점 계산
			 reviewAverageRatingDTO = sumReviewAverageRating.calculateAverageEnterpriseReviewRating();
			 
		 }
		 
		 return reviewAverageRatingDTO;
	}
	
}
