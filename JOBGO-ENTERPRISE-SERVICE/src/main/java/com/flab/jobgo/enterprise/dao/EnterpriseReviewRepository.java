package com.flab.jobgo.enterprise.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flab.jobgo.enterprise.entity.EnterpriseReview;

@Repository
public interface EnterpriseReviewRepository extends JpaRepository<EnterpriseReview, Integer>{

	@Query("select m from EnterpriseReview m where m.enterpriseInfo.enterpriseId = :enterpriseId")
	Slice<EnterpriseReview> findEnterpriseReviewByEnterpriseId(@Param("enterpriseId") int enterpriseId, Pageable pageable);
	
	@Query("select m from EnterpriseReview m where m.enterpriseInfo.enterpriseId = :enterpriseId")
	List<EnterpriseReview> findEnterpriseReviewByEnterpriseId(@Param("enterpriseId") int enterpriseId);
}
