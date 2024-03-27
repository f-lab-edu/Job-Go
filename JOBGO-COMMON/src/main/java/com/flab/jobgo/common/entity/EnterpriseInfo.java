package com.flab.jobgo.common.entity;

import com.flab.jobgo.common.constant.CommonConstant;
import com.flab.jobgo.common.dto.EnterpriseInfoResponseDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** 기업정보 | TB_ENTERPRISE_INFO */

@Entity
@Table(name = "TB_ENTERPRISE_INFO")
@Getter
@Setter // 변경감지 update를 위해 setter사용
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EnterpriseInfo {

    // 기업ID
    @Id
    @Column(name = "ENTERPRISE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enterpriseId;

    // 기업명
    @Column(name = "ENTERPRISE_NAME" ,nullable = false ,length = 64)
    private String enterpriseName;

    // 사업자등록번호
    @Column(name = "COMPANY_NUMBER" ,nullable = false ,length = 32)
    private String companyNumber;

    // 1차산업군코드
    @Column(name = "FS_INDUSTRY_CODE" ,nullable = false ,length = 8)
    private String fsIndustryCode;

    // 2차산업군코드
    @Column(name = "SC_INDUSTRY_CODE" ,nullable = false ,length = 8)
    private String scIndustryCode;

    // 담당자명
    @Column(name = "MANAGER_NAME" ,nullable = false ,length = 32)
    private String managerName;

    // 담당자연락처
    @Column(name = "CONTACT" ,nullable = false ,length = 32)
    private String contact;

    // 주소
    @Column(name = "ADDRESS" ,length = 256)
    private String address;

    // 설립일
    @Column(name = "ESTABLISH_DATE" ,length = 8)
    private String establishDate;

    // 기업형태코드
    @Column(name = "ENTERPRISE_TYPE_CODE" ,length = 8)
    private String enterpriseTypeCode;

    // 사원수
    @Column(name = "EMPLOYEES")
    private Integer employees;

    // 평균연봉
    @Column(name = "AVERAGE_SALARY")
    private Integer averageSalary;

    // 평균연봉수정일
    @Column(name = "UPDATE_DATE" ,length = 8)
    private String updateDate;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ENTERPRISE_USER_ID") // 외래키를 PK가 아닌 user_id로 설정
    private EnterpriseUser enterpriseUser;

    @Builder
	public EnterpriseInfo(Long enterpriseId, String enterpriseName, String companyNumber, String fsIndustryCode,
			String scIndustryCode, String managerName, String contact, String address, String establishDate,
			String enterpriseTypeCode, Integer employees, Integer averageSalary, String updateDate, EnterpriseUser enterpriseUser) {
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
		this.averageSalary = averageSalary;
		this.updateDate = updateDate;
		this.enterpriseUser = enterpriseUser;
	}
    
    public EnterpriseInfoResponseDTO transferToEnterpriseInfoResponseDTO() {
    	return EnterpriseInfoResponseDTO.builder()
    			.enterpriseId(enterpriseId)
		    	.enterpriseName(enterpriseName)
		    	.companyNumber(companyNumber)
		    	.managerName(managerName)
		    	.contact(contact)
		    	.address(address)
		    	.fsIndustry(CommonConstant.FirstIndustryEnum.getValue(fsIndustryCode))
		    	.fsIndustryCode(fsIndustryCode)
		    	.scIndustry(CommonConstant.SecondIndustryEnum.getValue(scIndustryCode))
		    	.scIndustryCode(scIndustryCode)
		    	.establishDate(establishDate)
		    	.employees(employees)
		    	.enterpriseType(CommonConstant.EnterpriseTypeEnum.getValue(enterpriseTypeCode))
		    	.enterpriseTypeCode(enterpriseTypeCode)
		    	.build();
    }
}


