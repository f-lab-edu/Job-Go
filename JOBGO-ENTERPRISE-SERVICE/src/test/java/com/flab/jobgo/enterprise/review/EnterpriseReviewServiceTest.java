package com.flab.jobgo.enterprise.review;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.flab.jobgo.enterprise.dto.EnterpriseReviewRequestDTO;
import com.flab.jobgo.enterprise.service.EnterpriseReviewService;


@SpringBootTest
public class EnterpriseReviewServiceTest {
	
	@Autowired 
	private EnterpriseReviewService service;

	@Test
	@DisplayName("리뷰할 기업 정보 조회 실패 테스트")
	public void findEnterpriseInfoFail() {
		EnterpriseReviewRequestDTO requestDTO = EnterpriseReviewRequestDTO.builder().build();
		
		assertThrows(NoSuchElementException.class, () -> {
			service.enterpriseReviewRegist(requestDTO, 2);
		});
	}
	
	@Test
	@DisplayName("리뷰 등록 성공 테스트")
	public void enterpriseReviewRegistSuccess() {
		EnterpriseReviewRequestDTO requestDTO = EnterpriseReviewRequestDTO.builder()
													.oneLineReview("한줄 평")
													.merit("장점")
													.disAdventage("단점")
													.totalRating(6)
													.benefitSalraryRating(5)
													.cultureRating(4)
													.potentialRating(3)
													.workLifeRating(2)
													.build();
		
		assertDoesNotThrow(() ->service.enterpriseReviewRegist(requestDTO, 1));
	}
}
