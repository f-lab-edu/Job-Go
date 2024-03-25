package com.flab.jobgo.enterprise.dto;

import com.flab.jobgo.common.entity.EnterpriseInfo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EnterpriseInfoRequestDTO {
	
	private Integer enterpriseId;
	
	private String enterpriseName;
	
	private String companyNumber;
	
	@NotBlank(message = "1차 산업군을 선택해 주세요.")
	private String fsIndustryCode;
	
	@NotBlank(message = "2차 산업군을 선택해 주세요.")
	private String scIndustryCode;
	
	@NotBlank(message = "담당자명은 필수 입력 입니다.")
	private String managerName;
	
	@NotBlank(message = "연락처는 필수 입력 입니다.")
	private String contact;
	
	private String address;
	
	private String establishDate;
	
	private String enterpriseTypeCode;
	
	private Integer employees;

	
	
	public EnterpriseInfo transferToEnterpriseInfo(EnterpriseInfo enterpriseInfo) {
		enterpriseInfo.setFsIndustryCode(fsIndustryCode);
		enterpriseInfo.setScIndustryCode(scIndustryCode);
		enterpriseInfo.setManagerName(managerName);
		enterpriseInfo.setContact(contact);
		enterpriseInfo.setAddress(address);
		enterpriseInfo.setEstablishDate(establishDate);
		enterpriseInfo.setEnterpriseTypeCode(enterpriseTypeCode);
		enterpriseInfo.setEmployees(employees);
		
		return enterpriseInfo;
	}

	@Builder
	public EnterpriseInfoRequestDTO(Integer enterpriseId, String enterpriseName, String companyNumber,
			@NotBlank(message = "1차 산업군을 선택해 주세요.") String fsIndustryCode,
			@NotBlank(message = "2차 산업군을 선택해 주세요.") String scIndustryCode,
			@NotBlank(message = "담당자명은 필수 입력 입니다.") String managerName,
			@NotBlank(message = "연락처는 필수 입력 입니다.") String contact, String address, String establishDate,
			String enterpriseTypeCode, Integer employees) {
		super();
		this.enterpriseId = enterpriseId;
		this.enterpriseName = enterpriseName;
		this.companyNumber = companyNumber;
		this.fsIndustryCode = fsIndustryCode;
		this.scIndustryCode = scIndustryCode;
		this.managerName = managerName;
		this.contact = contact;
		this.address = address;
		this.establishDate = establishDate;
		this.enterpriseTypeCode = enterpriseTypeCode;
		this.employees = employees;
	}
	
	
	
}
