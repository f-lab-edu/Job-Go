package com.flab.jobgo.enterprise.dto;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EnterpriseReviewResponseDTO {

	private int enterpriseReviewId;
	
	private int totalRating; // 총 평점
	
	private int potentialRating; // 성장 가능성 평점
	
	private int benefitSalraryRating; // 복지 및 급여 평점
	
	private int workLifeRating; // 업무와 삶의 균형 평점
	
	private int cultureRating; // 사내문화 평점
	
	private String oneLineReview; // 한줄 평
	
	private String merit; // 장점
	
	private String disAdventage; // 단점
	
	private String registrationDTM; // 등록일시 (yyyyMMddHHmmss)
	
	@Builder
	public EnterpriseReviewResponseDTO(int enterpriseReviewId, int totalRating, int potentialRating,
			int benefitSalraryRating, int workLifeRating, int cultureRating, String oneLineReview, String merit,
			String disAdventage, String registrationDTM) {
		super();
		this.enterpriseReviewId = enterpriseReviewId;
		this.totalRating = totalRating;
		this.potentialRating = potentialRating;
		this.benefitSalraryRating = benefitSalraryRating;
		this.workLifeRating = workLifeRating;
		this.cultureRating = cultureRating;
		this.oneLineReview = oneLineReview;
		this.merit = merit;
		this.disAdventage = disAdventage;
		this.registrationDTM = registrationDTM;
	}
}
