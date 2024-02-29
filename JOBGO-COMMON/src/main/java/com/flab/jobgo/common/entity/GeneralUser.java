package com.flab.jobgo.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

/** 일반회원 | TB_GENERAL_USER */

@Entity
@Table(name = "TB_GENERAL_USER")
@Getter
@Builder
public class GeneralUser{
	// 일반회원ID
	@Id
    @Column(name = "USER_ID")
    @GeneratedValue
    private Long userId;

    // 이메일
    @Column(name = "EMAIL" ,nullable = false ,length = 64)
    private String email;

    // 비밀번호
    @Column(name = "PW" ,nullable = false ,length = 32)
    private String pw;
    
	// 회원연락처
    @Column(name = "CONTACT" ,nullable = false ,length = 32)
    private String contact;

    // 성별
    @Column(name = "GENDER" ,nullable = false ,length = 8)
    private String gender;

    // 생년월일
    @Column(name = "BIRTH" ,nullable = false ,length = 8)
    private String birth;

    // 주소
    @Column(name = "ADDRESS" ,nullable = false ,length = 256)
    private String address;
}

