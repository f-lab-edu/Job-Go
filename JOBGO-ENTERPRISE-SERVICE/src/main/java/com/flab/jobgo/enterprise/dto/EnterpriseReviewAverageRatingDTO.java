package com.flab.jobgo.enterprise.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EnterpriseReviewAverageRatingDTO {
	
	private double totalRating; // 총 평점
	
	private double potentialRating; // 성장 가능성 평점
	
	private double benefitSalraryRating; // 복지 및 급여 평점
	
	private double workLifeRating; // 업무와 삶의 균형 평점
	
	private double cultureRating; // 사내문화 평점
	
	private int reviewCount; // 총 리뷰 개수
	
	
	
	// 총 리뷰 평점 계산을 위한 메소드
	public EnterpriseReviewAverageRatingDTO sumEnterpriseReviewRating(EnterpriseReviewAverageRatingDTO dto) {
		this.reviewCount += 1;
		
		this.totalRating += dto.getTotalRating();
		this.benefitSalraryRating += dto.getBenefitSalraryRating();
		this.cultureRating += dto.getCultureRating();
		this.potentialRating += dto.getPotentialRating();
		this.workLifeRating += dto.getWorkLifeRating();
		
		return this;
	}
	
	// 평균 리뷰 평점 계산을 위한 메소드
	public EnterpriseReviewAverageRatingDTO calculateAverageEnterpriseReviewRating() {
		this.totalRating = (double)Math.round((this.totalRating / reviewCount) * 10) / 10;
		this.benefitSalraryRating = (double)Math.round((this.benefitSalraryRating / reviewCount) * 10) / 10;
		this.cultureRating = (double)Math.round((this.cultureRating / reviewCount) * 10) / 10;
		this.potentialRating = (double)Math.round((this.potentialRating / reviewCount) * 10) / 10;
		this.workLifeRating = (double)Math.round((this.workLifeRating / reviewCount) * 10) / 10;
		
		return this;
	}

	@Builder
	public EnterpriseReviewAverageRatingDTO(int totalRating, int potentialRating, int benefitSalraryRating,
			int workLifeRating, int cultureRating, int reviewCount) {
		super();
		this.totalRating = totalRating;
		this.potentialRating = potentialRating;
		this.benefitSalraryRating = benefitSalraryRating;
		this.workLifeRating = workLifeRating;
		this.cultureRating = cultureRating;
		this.reviewCount = reviewCount;
	}
}
