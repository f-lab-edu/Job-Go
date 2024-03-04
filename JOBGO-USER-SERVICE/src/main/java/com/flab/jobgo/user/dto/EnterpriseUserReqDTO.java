package com.flab.jobgo.user.dto;

import com.flab.jobgo.common.entity.EnterpriseInfo;
import com.flab.jobgo.common.entity.EnterpriseUser;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EnterpriseUserReqDTO {

	private String userId;
	
	private String email;
	
	private String pw;
	
	private String enterpriseName;
	
	private String companyNumber;
	
	private String fsIndustryCode;
	
	private String scIndustryCode;
	
	private String managerName;
	
	private String contact;
	
	public EnterpriseUser transferToEnterpriseUser() {
		/*
		 * EnterpriseUser저장시 영속성을 전이시켜 EnterpriseInfo도 함께 저장하기 위해
		 * 양방향으로 매핑을 해주어야 한다. 
		 */
		EnterpriseUser enterpriseUser = EnterpriseUser.builder()
				.userId(userId)
				.pw(pw)
				.email(email)
				.build();
		
		EnterpriseInfo enterpriseInfo = transferToEnterpriseInfo(enterpriseUser);
		
		enterpriseUser.addEnterpriseInfo(enterpriseInfo);
		
		return enterpriseUser;
	}
	
	private EnterpriseInfo transferToEnterpriseInfo(EnterpriseUser enterpriseUser) {
		return EnterpriseInfo.builder()
			.enterpriseName(enterpriseName)
			.companyNumber(companyNumber)
			.fsIndustryCode(fsIndustryCode)
			.scIndustryCode(scIndustryCode)
			.managerName(managerName)
			.contact(contact)
			.enterpriseUser(enterpriseUser)
			.build();
	}

	@Builder
	private EnterpriseUserReqDTO(String userId, String email, String pw, String enterpriseName, String companyNumber,
			String fsIndustryCode, String scIndustryCode, String managerName, String contact) {
		super();
		this.userId = userId;
		this.email = email;
		this.pw = pw;
		this.enterpriseName = enterpriseName;
		this.companyNumber = companyNumber;
		this.fsIndustryCode = fsIndustryCode;
		this.scIndustryCode = scIndustryCode;
		this.managerName = managerName;
		this.contact = contact;
	}
	
	
}
