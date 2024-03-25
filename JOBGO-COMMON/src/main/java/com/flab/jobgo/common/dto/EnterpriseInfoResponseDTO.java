package com.flab.jobgo.common.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EnterpriseInfoResponseDTO {
	
	// 기업Id
	private Integer enterpriseId;
	
	// 기업명
	private String enterpriseName;
	
	// 사업자등록번호
	private String companyNumber;
	
	// 담당자명
	private String managerName;
	
	// 연락처
	private String contact;
	
	// 1차산업군코드
	private String fsIndustryCode;
	
	// 1차산업군
	private String fsIndustry;
	
	// 2차산업군코드
	private String scIndustryCode;
	
	// 2차산업군
	private String scIndustry;
	
	// 주소
	private String address;
	
	// 설립일
	private String establishDate;
	
	// 기업형태코드
	private String enterpriseTypeCode;
	
	// 기업 형태
	private String enterpriseType;
	
	// 사원수
	private Integer employees;
	
	// 평균연봉
	private Integer averageSalary;
	
	@Builder
	public EnterpriseInfoResponseDTO(Integer enterpriseId, String enterpriseName, String companyNumber, String managerName, String contact,
			String fsIndustryCode, String fsIndustry, String scIndustryCode, String scIndustry, String address,
			String establishDate, String enterpriseTypeCode, String enterpriseType, Integer employees,
			Integer averageSalary) {
		super();
		this.enterpriseId = enterpriseId;
		this.enterpriseName = enterpriseName;
		this.companyNumber = companyNumber;
		this.managerName = managerName;
		this.contact = contact;
		this.fsIndustryCode = fsIndustryCode;
		this.fsIndustry = fsIndustry;
		this.scIndustryCode = scIndustryCode;
		this.scIndustry = scIndustry;
		this.address = address;
		this.establishDate = establishDate;
		this.enterpriseTypeCode = enterpriseTypeCode;
		this.enterpriseType = enterpriseType;
		this.employees = employees;
		this.averageSalary = averageSalary;
	}
	
	
}
