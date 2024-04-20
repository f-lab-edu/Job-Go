package com.flab.jobgo.user.controller;

import java.time.Duration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.jobgo.common.constant.CommonConstant;
import com.flab.jobgo.common.entity.JwtToken;
import com.flab.jobgo.common.service.JwtTokenStorageService;
import com.flab.jobgo.common.dto.ResponseDTO;
import com.flab.jobgo.common.utils.ResponseGenerateUtil;
import com.flab.jobgo.user.dto.GeneralUserReqDTO;
import com.flab.jobgo.user.dto.UserLoginRequestDTO;
import com.flab.jobgo.user.service.GeneralUserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/general")
public class GeneralUserController {

	private final GeneralUserService userService;
	private final JwtTokenStorageService jwtTokenStorageService;
	
	@PostMapping
	public ResponseEntity<ResponseDTO> generalUserRegist(@RequestBody @Valid GeneralUserReqDTO userReqDTO, Errors errors){
		if(errors.hasErrors()) {
			FieldError fieldError = errors.getFieldError();
			return ResponseGenerateUtil.generateResponse(fieldError.getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
		
		userService.userRegist(userReqDTO);
		
		return ResponseGenerateUtil.generateResponse(CommonConstant.USER_REGIST_SUCCESS, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<ResponseDTO> enterpriseUserLogin(@RequestBody UserLoginRequestDTO userLoginDto, HttpServletResponse response){
		JwtToken jwtToken = userService.generalUserLogin(userLoginDto);
	
		String accessToken = jwtToken.getAccessToken();
		String refreshToken = jwtToken.getRefreshToken();
		
		// refreshToken은 cookie에 저장
		Cookie cookie = new Cookie("refreshToken", refreshToken);
		response.addCookie(cookie);
		
		// redis저장소에 jwtToken저장
		jwtTokenStorageService.saveJwtToken(jwtToken);
		
		// accessToken은 header에 저장
		return ResponseEntity.ok()
				.header("accessToken", accessToken)
				.body(new ResponseDTO("success", HttpStatus.OK.value()));	
	}
}
