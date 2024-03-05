package com.flab.jobgo.user.enterprise;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.flab.jobgo.common.dao.EnterPriseUserRepository;
import com.flab.jobgo.common.entity.EnterpriseUser;
import com.flab.jobgo.user.dto.EnterpriseUserReqDTO;

@SpringBootTest
public class EnterpriseUserRepositoryTest {

	@Autowired
	private EnterPriseUserRepository repo;
	
	@Test
	public void enterpriseUserRegist() {
		EnterpriseUserReqDTO userReqDTO = EnterpriseUserReqDTO.builder()
			.userId("userA")
			.pw("1234")
			.email("aaaaa")
			.enterpriseName("기업A")
			.companyNumber("1234567890")
			.fsIndustryCode("001")
			.scIndustryCode("001")
			.managerName("담당자A")
			.contact("01011111111")
			.build();
		
		EnterpriseUser enterpriseUser = userReqDTO.transferToEnterpriseUser();
		repo.save(enterpriseUser);
	}
}
