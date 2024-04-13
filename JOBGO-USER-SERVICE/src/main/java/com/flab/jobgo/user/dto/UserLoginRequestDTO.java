package com.flab.jobgo.user.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserLoginRequestDTO {

	private String userId;
	
	private String password;

	@Builder
	public UserLoginRequestDTO(String userId, String password) {
		this.userId = userId;
		this.password = password;
	}
	
	
}
