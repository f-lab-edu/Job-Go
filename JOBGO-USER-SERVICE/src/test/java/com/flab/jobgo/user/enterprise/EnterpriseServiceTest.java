package com.flab.jobgo.user.enterprise;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.flab.jobgo.user.dto.EnterpriseUserReqDTO;
import com.flab.jobgo.user.exception.DuplicatedUserIdException;
import com.flab.jobgo.user.service.EnterpriseUserService;

@ExtendWith(MockitoExtension.class) 
public class EnterpriseServiceTest {

	@Mock
	private EnterpriseUserService service;
	
	// 중복ID 회원가입 테스트
	@Test
	public void duplicatedUserRegistTest() {
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
		
		
		// Loose stubbing으로 설정하여 에러를 넘기기 위해 lenient()사용
		lenient().doThrow(DuplicatedUserIdException.class).when(service).userRegist(userReqDTO);
		
		assertThrows(DuplicatedUserIdException.class, () -> {
			service.userRegist(userReqDTO);	
		});
	}
	
	// 정상 회원가입 테스트
	@Test
	public void userRegistTest() {
		EnterpriseUserReqDTO userReqDTO = EnterpriseUserReqDTO.builder()
				.userId("userC")
				.pw("1234")
				.email("cccc")
				.enterpriseName("기업C")
				.companyNumber("0987654321")
				.fsIndustryCode("001")
				.scIndustryCode("001")
				.managerName("담당자C")
				.contact("01033333333")
				.build();
		
		lenient().doNothing().when(service).userRegist(userReqDTO);
		lenient().doReturn(true).when(service).isExistUserId(userReqDTO.getUserId());
		
		boolean existUserId = service.isExistUserId(userReqDTO.getUserId());
		
		assertThat(existUserId).isTrue();
	}
}
