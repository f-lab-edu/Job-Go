package com.flab.jobgo.enterprise.info;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.flab.jobgo.common.dto.EnterpriseInfoResponseDTO;
import com.flab.jobgo.common.entity.EnterpriseInfo;
import com.flab.jobgo.enterprise.dao.EnterpriseInfoRepository;
import com.flab.jobgo.enterprise.service.EnterpriseInfoService;

@ExtendWith(MockitoExtension.class)
public class EnterpriseInfoServiceTest {

	@Mock
	private EnterpriseInfoRepository repository;
	
	@InjectMocks
	private EnterpriseInfoService service;
	
	@Test
	public void findEnterpriseInfoByUserIdTest() {
		EnterpriseInfo enterpriseInfo = EnterpriseInfo.builder()
			.companyNumber("123456")
			.enterpriseName("회사명A")
			.managerName("담당자A")
			.contact("01012345678")
			.fsIndustryCode("001")
			.scIndustryCode("001")
			.enterpriseTypeCode("001")
			.build();
		
		doReturn(enterpriseInfo).when(repository).findByUserId("aaa");
		
		EnterpriseInfoResponseDTO responseDTO = service.findEnterpriseInfoByUserId("aaa");
		
		assertThat(responseDTO.getFsIndustry()).isEqualTo("서비스업");
		assertThat(responseDTO.getScIndustry()).isEqualTo("호텔/여행/항공");
		assertThat(responseDTO.getEnterpriseType()).isEqualTo("중소기업");
	}
	
}
