package com.flab.jobgo.enterprise.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flab.jobgo.enterprise.entity.EnterpriseReview;

@Repository
public interface EnterpriseReviewRepository extends JpaRepository<EnterpriseReview, Integer>{

}
