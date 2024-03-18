package com.flab.jobgo.user.dto;

import com.flab.jobgo.common.entity.GeneralUser;
import com.flab.jobgo.user.utils.PasswordEncoder;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GeneralUserReqDTO {

	@NotBlank(message = "아이디는 필수 입력 입니다.")
    private String userId;
	
	@NotBlank(message = "사용자명은 필수 입력 입니다.")
	private String userName;

	@NotBlank(message = "이메일은 필수 입력 입니다.")
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

	@NotBlank(message = "비밀번호는 필수 입력 입니다.")
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{10,16}", message = "비밀번호는 10~16자 영문 대 소문자, 숫자, 특수문자 형식이어야 합니다.")
    private String pw;
    
	@NotBlank(message = "연락처는 필수 입력 입니다.")
    private String contact;

	@NotBlank(message = "성별은 필수 입력 입니다.")
    private String gender;

	@NotBlank(message = "생년월일은 필수 입력 입니다.")
    private String birth;

	@NotBlank(message = "주소는 필수 입력 입니다.")
    private String address;

	public GeneralUser transferToGeneralUser() {
		return GeneralUser.builder()
				.userId(userId)
				.pw(PasswordEncoder.encode(pw))
				.email(email)
				.userName(userName)
				.contact(contact)
				.birth(birth)
				.gender(gender)
				.address(address)
				.build();
	}
	
    @Builder
	public GeneralUserReqDTO(String userId, String userName, String email, String pw, String contact, String gender,
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
