package com.flab.jobgo.common.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flab.jobgo.common.entity.GeneralUser;

/*
 * User관련 기능은 회원가입, 회원조회, 로그인, ID중복체크 등 간단한 CRUD 기능만 있기 때문에 
 * JPA에서 제공하는 공통 인터페이스를 상속
 */
@Repository
public interface GeneralUserRepository extends JpaRepository<GeneralUser, String>{

	//일반회원 로그인시 검증
	GeneralUser findByUserId(String userId);
	
	boolean existsByUserId(String userId);
}
