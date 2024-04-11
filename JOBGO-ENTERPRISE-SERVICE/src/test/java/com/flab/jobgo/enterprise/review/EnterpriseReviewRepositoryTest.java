package com.flab.jobgo.enterprise.review;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import com.flab.jobgo.enterprise.dao.EnterpriseReviewRepository;
import com.flab.jobgo.enterprise.entity.EnterpriseReview;

@SpringBootTest
public class EnterpriseReviewRepositoryTest {

	@Autowired
	private EnterpriseReviewRepository repository;
	
	@Test
	public void findReviewByEnterpriseIdTest() {
		
		PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "registrationDTM"));
		
		Slice<EnterpriseReview> reviewByEnterpriseId = repository.findEnterpriseReviewByEnterpriseId(2, pageRequest);
		
		List<EnterpriseReview> list = reviewByEnterpriseId.getContent();
		
		assertThat(list.size()).isEqualTo(1);
	}
}
