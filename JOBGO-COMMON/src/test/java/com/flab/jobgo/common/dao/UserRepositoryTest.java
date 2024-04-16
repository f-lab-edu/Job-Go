package com.flab.jobgo.common.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.flab.jobgo.common.entity.EnterpriseUser;
import com.flab.jobgo.common.entity.GeneralUser;

@SpringBootTest
public class UserRepositoryTest {

	private final GeneralUserRepository generalUserRepo;
	private final EnterPriseUserRepository enterpriseUserRepo;
	
	@Autowired
	public UserRepositoryTest(GeneralUserRepository generalUserRepo, EnterPriseUserRepository enterpriseUserRepo) {
		super();
		this.generalUserRepo = generalUserRepo;
		this.enterpriseUserRepo = enterpriseUserRepo;
	}

	@Test
	@Transactional // 테스트데이터는 롤백처리
	void userRegistAndLoginTest() {
		GeneralUser generalUser = GeneralUser.builder()
			.userId("userA")
			.pw("1111")
			.address("대전 동구")
			.birth("20001111")
			.contact("01011111111")
			.email("abcd@abcd.com")
			.gender("남")
			.userName("홍길동")
			.build();
		
		// 일반회원 등록
		generalUserRepo.save(generalUser);
		
		EnterpriseUser enterpriseUser = EnterpriseUser.builder()
			.userId("userB")
			.pw("1234")
			.email("aaaa@aaaa.com")
			.build();
		
		// 기업회원 등록
		enterpriseUserRepo.save(enterpriseUser);
		
		// 일반회원 로그인 -> 실패처리
		GeneralUser findGeneralUser = generalUserRepo.findByUserId("userA");
		
//		 기업회원 로그인 -> 성공처리
		EnterpriseUser findEnterpriseUser = enterpriseUserRepo.findByUserId("userB");
		
		
		assertThat(findGeneralUser).isNull();
		assertThat(findEnterpriseUser).isNotNull();
	}
	
}
