package com.flab.jobgo.user.enterprise;

import static org.mockito.Mockito.lenient;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.jobgo.common.entity.GeneralUser;
import com.flab.jobgo.user.controller.GeneralUserController;
import com.flab.jobgo.user.dto.GeneralUserReqDTO;
import com.flab.jobgo.user.service.GeneralUserService;

@ExtendWith(MockitoExtension.class)
public class GeneralUserMvcTest {

	@InjectMocks
	private GeneralUserController controller;
	
	@Mock
	private GeneralUserService service;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	// 정상 회원가입 테스트 
	@Test
	public void generalUserRegistMvcSuccessTest() throws Exception {
		// 1.GeneralUserService의 userRegist()메서드 호출시 성공처리 하도록 설정 
		lenient().doNothing().when(service).userRegist(ArgumentMatchers.any(GeneralUserReqDTO.class));
		
		GeneralUser user = GeneralUser.builder()
							.userId("userA")
							.userName("사용자명A")
							.email("abcd@abcd.com")
							.pw("1q2w3e4r5t!")
							.address("dadsa")
							.contact("01011111111")
							.gender("남자")
							.birth("961127")
							.build();
		
		// 2. 요청 본문에 작성할 json변환
		String writeValueAsString = new ObjectMapper().writeValueAsString(user);
		
		// 3.GeneralUserService를 주입받은 GeneralUserController에 요청 정보를 담아 호출
		ResultActions actions = mockMvc.perform(
				MockMvcRequestBuilders.post("/general/user").contentType(MediaType.APPLICATION_JSON).content(writeValueAsString)
		);
		
		// 4.응답 검증
		actions.andExpect(status().isOk());
	}
	
	// 회원가입 유효성 검사 실패 테스트
	@Test
	public void generalUserRegistValidFailTest() throws Exception {
		
		// 1. 이메일 패턴 유효성 검사 실패하도록 설정
		GeneralUser user = GeneralUser.builder()
				.userId("userA")
				.userName("사용자명A")
				.email("aaaaa")
				.pw("1q2w3e4r5t!")
				.address("dadsa")
				.contact("01011111111")
				.gender("남자")
				.birth("961127")
				.build();

		// 2. 요청 본문에 작성할 json변환
		String writeValueAsString = new ObjectMapper().writeValueAsString(user);
		
		// 3.GeneralUserService를 주입받은 GeneralUserController에 요청 정보를 담아 호출
		ResultActions actions = mockMvc.perform(
			MockMvcRequestBuilders.post("/general/user").contentType(MediaType.APPLICATION_JSON).content(writeValueAsString)
		);
		
		// 4.응답 검증
		actions.andExpect(status().is4xxClientError());
	}
}
