package com.flab.jobgo.common.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/** 기업회원 | TB_ENTERPRISE_USER */
@Entity
@Table(name = "TB_ENTERPRISE_USER")
@Getter	
@NoArgsConstructor(access = AccessLevel.PROTECTED) // Entity는 기본생성자가 필수이다. 하지만 무의미한 Entity객체 생성을 막기위해 접근제어자는 protected로 열어놓자
public class EnterpriseUser {
    @Id
    @Column(name = "ENTERPRISE_USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MySQL의 IDENTITY전략은   Auto Increment로 적용 
    private Long id;

    // 기업회원ID
    @Column(name = "USER_ID" ,nullable = false ,length = 32, unique = true) // 식별자로서  uniqe필드로 지정
    private String userId;

    // 이메일
    @Column(name = "EMAIL" ,nullable = false ,length = 64)
    private String email;

    // 비밀번호
    @Column(name = "PW" ,nullable = false ,length = 100)
    private String pw;
    
    // 기업정보
    // 기업회원 등록,탈퇴시 기업정보도 함께 저장,삭제되기 위해 양방향 매핑하여 영속성 전이
    @OneToOne(mappedBy = "enterpriseUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL) 
    private EnterpriseInfo enterpriseInfo;

    @Builder //의도가 명확하지 않은 변경을 막기위해 Setter를 사용하지 않았다. 그래서 의미있는 객체를 생성하기 위해 Builder를 사용
	public EnterpriseUser(String userId, String email, String pw) {
		super();
		this.userId = userId;
		this.email = email;
		this.pw = pw;
	}
    // 영속성 전이로 EnterpriseInfo를 같이 저장하기 위해서는 EnterpriseUser는 EnterpriseInfo정보를 가지고 있어야한다.
    public void addEnterpriseInfo(EnterpriseInfo enterpriseInfo) {
    	this.enterpriseInfo = enterpriseInfo;
    }
    
    
}

