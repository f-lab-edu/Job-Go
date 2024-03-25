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
import com.flab.jobgo.common.dto.ResponseDTO;
import com.flab.jobgo.common.utils.ResponseGenerateUtil;
import com.flab.jobgo.user.dto.GeneralUserReqDTO;
import com.flab.jobgo.user.service.GeneralUserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/general/user")
public class GeneralUserController {

	private final GeneralUserService userService;
	
	@PostMapping
	public ResponseEntity<ResponseDTO> generalUserRegist(@RequestBody @Valid GeneralUserReqDTO userReqDTO, Errors errors){
		if(errors.hasErrors()) {
			FieldError fieldError = errors.getFieldError();
			return ResponseGenerateUtil.generateResponse(fieldError.getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
		
		userService.userRegist(userReqDTO);
		
		return ResponseGenerateUtil.generateResponse(CommonConstant.USER_REGIST_SUCCESS, HttpStatus.OK);
	}
}
