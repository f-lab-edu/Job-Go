package com.flab.jobgo.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/** 일반회원 | TB_GENERAL_USER */


@Entity
@Table(name = "TB_GENERAL_USER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //Entity는 기본생성자가 필수이다. 하지만 무의미한 Entity객체 생성을 막기위해 접근제어자는 protected로 열어놓자
public class GeneralUser{
	// 일반회원ID
	@Id
    @Column(name = "USER_ID", length = 32)
    private String userId;
	
	@Column(name = "USER_NAME", nullable = false, length = 32)
	private String userName;

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

    @Builder //의도가 명확하지 않은 변경을 막기위해 Setter를 사용하지 않았다. 그래서 의미있는 객체를 생성하기 위해 Builder를 사용 
	public GeneralUser(String userId, String userName, String email, String pw, String contact, String gender,
			String birth, String address) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.pw = pw;
		this.contact = contact;
		this.gender = gender;
		this.birth = birth;
		this.address = address;
	}
    
    
}

