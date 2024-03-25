package com.flab.jobgo.enterprise.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.jobgo.common.constant.CommonConstant;
import com.flab.jobgo.common.dto.ResponseDTO;
import com.flab.jobgo.common.utils.ResponseGenerateUtil;
import com.flab.jobgo.enterprise.dto.EnterpriseReviewRequestDTO;
import com.flab.jobgo.enterprise.service.EnterpriseReviewService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/enterprise/review")
@RequiredArgsConstructor
public class EnterpriseReviewContoller {

	private final EnterpriseReviewService service;
	
	@PostMapping("/{enterprise-id}")
	public ResponseEntity<ResponseDTO> enterpriseReviewRegist(@RequestBody @Valid EnterpriseReviewRequestDTO enterpriseReviewRequestDTO,
			Errors errors, @PathVariable("enterprise-id")int enterpriseId){
		
		if(errors.hasErrors()) {
			FieldError fieldError = errors.getFieldError();
			return ResponseGenerateUtil.generateResponse(fieldError.getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
		
		service.enterpriseReviewRegist(enterpriseReviewRequestDTO, enterpriseId);
	
		return ResponseGenerateUtil.generateResponse(CommonConstant.REVIEW_REGIST_SUCCESS, HttpStatus.OK);
	}
	
}
