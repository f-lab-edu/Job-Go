package com.flab.jobgo.enterprise.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.flab.jobgo.enterprise.entity.EnterpriseReview;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EnterpriseReviewRequestDTO {

	@NotBlank(message = "한줄 평을 입력해 주세요.")
	private String oneLineReview;
	
	@NotBlank(message = "장점을 입력해 주세요.")
	private String merit;
	
	@NotBlank(message = "단점을 입력해 주세요.")
	private String disAdventage; 
	
	private int totalRating;
	
	private int potentialRating;
	
	private int benefitSalraryRating;
	
	private int workLifeRating;
	
	private int cultureRating;

	@Builder
	public EnterpriseReviewRequestDTO(@NotBlank(message = "한줄 평을 입력해 주세요.") String oneLineReview,
			@NotBlank(message = "장점을 입력해 주세요.") String merit, @NotBlank(message = "단점을 입력해 주세요.") String disAdventage,
			@NotBlank(message = "총 평점을 입력해 주세요.") int totalRating,
			@NotBlank(message = "모든 항목의 평점을 입력해 주세요.") int potentialRating,
			@NotBlank(message = "모든 항목의 평점을 입력해 주세요.") int benefitSalraryRating,
			@NotBlank(message = "모든 항목의 평점을 입력해 주세요.") int workLifeRating,
			@NotBlank(message = "모든 항목의 평점을 입력해 주세요.") int cultureRating) {
		super();
		this.oneLineReview = oneLineReview;
		this.merit = merit;
		this.disAdventage = disAdventage;
		this.totalRating = totalRating;
		this.potentialRating = potentialRating;
		this.benefitSalraryRating = benefitSalraryRating;
		this.workLifeRating = workLifeRating;
		this.cultureRating = cultureRating;
	}
	
	public EnterpriseReview transferToEnterpriseReview() {
		// 등록일시, 등록자 id는 AOP로 값 셋팅 구현 예정
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String dtm = format.format(new Date());
		
		return EnterpriseReview.builder()
				.oneLineReview(oneLineReview)
				.merit(merit)
				.disAdventage(disAdventage)
				.potentialRating(potentialRating)
				.benefitSalraryRating(benefitSalraryRating)
				.cultureRating(cultureRating)
				.workLifeRating(workLifeRating)
				.totalRating(totalRating)
				.registrationDTM(dtm)
				.registrationUserId("enterpriseUserA")
				.build();
	}
}
