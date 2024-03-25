package com.flab.jobgo.enterprise.entity;

import com.flab.jobgo.common.entity.EnterpriseInfo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_ENTERPRISE_REVIEW_INFO")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class EnterpriseReview {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ENTERPRISE_REVIEW_ID")
	private int enterpriseReviewId;
	
	@Column(name = "TOTAL_RATING", nullable = false)
	private int totalRating; // 총 평점
	
	@Column(name = "POTENTIAL_RATING", nullable = false)
	private int potentialRating; // 성장 가능성 평점
	
	@Column(name = "BENEFIT_SALARY_RATING", nullable = false)
	private int benefitSalraryRating; // 복지 및 급여 평점
	
	@Column(name = "WORK_LIFE_RATING", nullable = false)
	private int workLifeRating; // 업무와 삶의 균형 평점
	
	@Column(name = "CULTURE_RATING", nullable = false)
	private int cultureRating; // 사내문화 평점
	
	@Column(name = "ONELINE_REVIEW", nullable = false, length = 64)
	private String oneLineReview; // 한줄 평
	
	@Column(name = "MERIT", nullable = false, length = 256)
	private String merit; // 장점
	
	@Column(name = "DISADVANTAGE", nullable = false, length = 256)
	private String disAdventage; // 단점
	
	@Column(name = "REGISTRATION_DTM", nullable = false, length = 14)
	private String registrationDTM; // 등록일시 (yyyyMMddHHmmss)
	
	@Column(name = "REGISTRATION_USER_ID", nullable = false, length = 32)
	private String registrationUserId; // 등록자Id
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ENTERPRISE_ID")
	private EnterpriseInfo enterpriseInfo;

	@Builder
	public EnterpriseReview(int enterpriseReviewId, int totalRating, int potentialRating, int benefitSalraryRating,
			int workLifeRating, int cultureRating, String oneLineReview, String merit, String disAdventage,
			String registrationDTM, String registrationUserId, EnterpriseInfo enterpriseInfo) {
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
		this.registrationUserId = registrationUserId;
		this.enterpriseInfo = enterpriseInfo;
	}
	
	public void addEnterpriseInfo(EnterpriseInfo enterpriseInfo) {
		this.enterpriseInfo = enterpriseInfo;
	}
	
	
}
