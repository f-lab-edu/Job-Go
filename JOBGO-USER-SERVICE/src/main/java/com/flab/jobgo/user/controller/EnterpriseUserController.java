package com.flab.jobgo.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.jobgo.common.constant.CommonConstant;
import com.flab.jobgo.common.dto.JwtToken;
import com.flab.jobgo.common.dto.ResponseDTO;
import com.flab.jobgo.common.utils.ResponseGenerateUtil;
import com.flab.jobgo.user.dto.EnterpriseUserReqDTO;
import com.flab.jobgo.user.dto.UserLoginRequestDTO;
import com.flab.jobgo.user.service.EnterpriseUserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/enterprise")
public class EnterpriseUserController {

	private final EnterpriseUserService userService;
	
	@PostMapping
	public ResponseEntity<ResponseDTO> enterpriseUserRegist(@RequestBody @Valid EnterpriseUserReqDTO userReqDTO, Errors errors){
		if(errors.hasErrors()) {
			FieldError fieldError = errors.getFieldError();
			return ResponseGenerateUtil.generateResponse(fieldError.getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
		
		userService.userRegist(userReqDTO);
		
		return ResponseGenerateUtil.generateResponse(CommonConstant.USER_REGIST_SUCCESS, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtToken> enterpriseUserLogin(@RequestBody UserLoginRequestDTO userLoginDto){
		JwtToken jwtToken = userService.enterpriseUserLogin(userLoginDto);
		return new ResponseEntity<JwtToken>(jwtToken, HttpStatus.OK);	
	}
}
