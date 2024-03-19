package com.flab.jobgo.enterprise.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flab.jobgo.common.entity.EnterpriseInfo;

@Repository
public interface EnterpriseInfoRepository extends JpaRepository<EnterpriseInfo, String>{
	
	EnterpriseInfo findByUserId(String userId);
}
