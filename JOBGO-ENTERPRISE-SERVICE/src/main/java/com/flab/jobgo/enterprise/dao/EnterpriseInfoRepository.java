package com.flab.jobgo.enterprise.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flab.jobgo.common.entity.EnterpriseInfo;

@Repository
public interface EnterpriseInfoRepository extends JpaRepository<EnterpriseInfo, Integer>{
	
	// JPQL은 엔티티 객체를 대상으로 쿼리를 생성한다. 따라서 userId로 EnterpriseInfo를 조회하기 위해서는 연관관계인 enterpriseUser를 참조해야한다.
	@Query("select m from EnterpriseInfo m where m.enterpriseUser.userId = :userId")
	EnterpriseInfo findByUserId(@Param("userId")String userId);
}
