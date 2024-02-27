package com.flab.jobgo.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

/** 기업회원 | TB_ENTERPRISE_USER */
@Entity
@Table(name = "TB_ENTERPRISE_USER")
@Builder
@Getter
public class EnterpriseUser {
	// 기업회원ID
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
}

