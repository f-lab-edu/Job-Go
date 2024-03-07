package com.flab.jobgo.user.dto;

import com.flab.jobgo.common.entity.EnterpriseInfo;
import com.flab.jobgo.common.entity.EnterpriseUser;
import com.flab.jobgo.user.utils.PasswordEncoder;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EnterpriseUserReqDTO{
	
	@NotBlank(message = "아이디는 필수 입력 입니다.")
	public String userId;
	
	@NotBlank(message = "비밀번호는 필수 입력 입니다.")
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{10,16}", message = "비밀번호는 10~16자 영문 대 소문자, 숫자, 특수문자 형식이어야 합니다.")
	public String pw;
	
	@NotBlank(message = "이메일은 필수 입력 입니다.")
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
	public String email;
	
	@NotBlank(message = "연락처는 필수 입력 입니다.")
	public String contact;
	
	@NotBlank(message = "기업명은 필수 입력 입니다.")
	private String enterpriseName;
	
	@NotBlank(message = "사업자 번호는 필수 입력 입니다.")
	private String companyNumber;
	
	@NotBlank(message = "1차 산업분류를 선택 해 주세요.")
	private String fsIndustryCode;
	
	@NotBlank(message = "2차 산업분류를 선택 해 주세요.")
	private String scIndustryCode;
	
	@NotBlank(message = "담당자명은 필수 입력 입니다.")
	private String managerName;
	
	public EnterpriseUser transferToEnterpriseUser() {
		/*
		 * EnterpriseUser저장시 영속성을 전이시켜 EnterpriseInfo도 함께 저장하기 위해
		 * 양방향으로 매핑을 해주어야 한다. 
		 */
		EnterpriseUser enterpriseUser = EnterpriseUser.builder()
				.userId(userId)
				.pw(PasswordEncoder.encode(pw))
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
