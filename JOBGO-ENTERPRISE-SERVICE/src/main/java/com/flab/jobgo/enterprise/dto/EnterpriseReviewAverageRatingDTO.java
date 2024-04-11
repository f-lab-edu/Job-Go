package com.flab.jobgo.enterprise.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EnterpriseReviewAverageRatingDTO {
	// BigDecimal : 
	// 소숫점을 다루는 계산이 필요할 경우 double, long과 같은 실수타입을 사용하게되면 실제 예상 결과값과 다른 근사치 값으로 나올 수 있기 때문에  BigDecimal을 사용하는 것이 좋다.
	
	private BigDecimal totalRating; // 총 평점
	
	private BigDecimal potentialRating; // 성장 가능성 평점
	
	private BigDecimal benefitSalraryRating; // 복지 및 급여 평점
	
	private BigDecimal workLifeRating; // 업무와 삶의 균형 평점
	
	private BigDecimal cultureRating; // 사내문화 평점
	
	private BigDecimal reviewCount; // 총 리뷰 개수
	
	
	
	// 총 리뷰 평점 계산을 위한 메소드
	public EnterpriseReviewAverageRatingDTO sumEnterpriseReviewRating(EnterpriseReviewAverageRatingDTO dto) {
		this.reviewCount = this.reviewCount.add(BigDecimal.ONE);
		this.totalRating = this.totalRating.add(dto.getTotalRating());
		this.benefitSalraryRating = this.benefitSalraryRating.add(dto.getBenefitSalraryRating());
		this.cultureRating = this.cultureRating.add(dto.getCultureRating());
		this.potentialRating = this.potentialRating.add(dto.getPotentialRating());
		this.workLifeRating = this.workLifeRating.add(dto.getWorkLifeRating());
		return this;
	}
	
	// 평균 리뷰 평점 계산을 위한 메소드
	public EnterpriseReviewAverageRatingDTO calculateAverageEnterpriseReviewRating() {
		this.totalRating = this.totalRating.divide(reviewCount, 1, RoundingMode.HALF_EVEN);
		this.benefitSalraryRating = this.benefitSalraryRating.divide(reviewCount, 1, RoundingMode.HALF_EVEN);
		this.cultureRating = this.cultureRating.divide(reviewCount, 1, RoundingMode.HALF_EVEN);
		this.potentialRating = this.potentialRating.divide(reviewCount, 1, RoundingMode.HALF_EVEN);
		this.workLifeRating = this.workLifeRating.divide(reviewCount, 1, RoundingMode.HALF_EVEN);
		
		return this;
	}

	@Builder
	public EnterpriseReviewAverageRatingDTO(BigDecimal totalRating, BigDecimal potentialRating, BigDecimal benefitSalraryRating,
			BigDecimal workLifeRating, BigDecimal cultureRating, BigDecimal reviewCount) {
		super();
		this.totalRating = totalRating;
		this.potentialRating = potentialRating;
		this.benefitSalraryRating = benefitSalraryRating;
		this.workLifeRating = workLifeRating;
		this.cultureRating = cultureRating;
		this.reviewCount = reviewCount;
	}
}
