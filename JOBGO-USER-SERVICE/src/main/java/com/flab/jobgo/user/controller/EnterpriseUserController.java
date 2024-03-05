package com.flab.jobgo.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.jobgo.common.dto.ResponseDTO;
import com.flab.jobgo.user.dto.EnterpriseUserReqDTO;
import com.flab.jobgo.user.service.EnterpriseUserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/enterprise/user")
public class EnterpriseUserController {

	private final EnterpriseUserService userService;
	
	@PostMapping
	public ResponseEntity<ResponseDTO> enterpriseUserRegist(@RequestBody @Valid EnterpriseUserReqDTO userReqDTO, Errors errors){
		ResponseDTO responseDTO = null;
		ResponseEntity<ResponseDTO> responseEntity = null;
		
		if(errors.hasErrors()) {
			FieldError fieldError = errors.getFieldError();
			responseDTO = new ResponseDTO(fieldError.getDefaultMessage(), HttpStatus.BAD_REQUEST.value());
			responseEntity = new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
			return responseEntity;
		}
		
		userService.userRegist(userReqDTO);
		responseDTO = new ResponseDTO("회원가입이 완료됐습니다.", HttpStatus.OK.value());
		responseEntity = new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
		
		return responseEntity;
	}
}
