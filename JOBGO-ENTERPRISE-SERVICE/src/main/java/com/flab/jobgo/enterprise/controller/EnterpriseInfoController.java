package com.flab.jobgo.enterprise.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.jobgo.common.constant.CommonConstant;
import com.flab.jobgo.common.dto.EnterpriseInfoResponseDTO;
import com.flab.jobgo.common.dto.ResponseDTO;
import com.flab.jobgo.common.utils.ResponseGenerateUtil;
import com.flab.jobgo.enterprise.dto.EnterpriseInfoRequestDTO;
import com.flab.jobgo.enterprise.service.EnterpriseInfoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/enterprise/info")
public class EnterpriseInfoController {

	private final EnterpriseInfoService service;
	
	@GetMapping
	public ResponseEntity<EnterpriseInfoResponseDTO> findEnterpriseInfoByUserId(){
		// 사용자 인증 로직 적용 후 userId를 인증정보에서 조회하여 사용하도록 수정 예정
		EnterpriseInfoResponseDTO responseDTO = service.findEnterpriseInfoByUserId("enterpriseUserA");
		
		return new ResponseEntity<EnterpriseInfoResponseDTO>(responseDTO, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<ResponseDTO> updateEnterpriseInfo(@RequestBody @Valid EnterpriseInfoRequestDTO enterpriseInfoReqDTO, Errors errors){
		if(errors.hasErrors()) {
			FieldError fieldError = errors.getFieldError();
			return ResponseGenerateUtil.generateResponse(fieldError.getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
		
		service.updateEnterpriseInfo(enterpriseInfoReqDTO);
		
		return ResponseGenerateUtil.generateResponse(CommonConstant.INFO_UPDATE_SUCCESS, HttpStatus.OK);
	}
	
	@GetMapping("/{enterprise-id}")
	public ResponseEntity<EnterpriseInfoResponseDTO> findEnterpriseInfoByPK(@PathVariable(name = "enterprise-id")Integer enterpriseId){
		EnterpriseInfoResponseDTO responseDTO = service.findEnterpriseInfoByPK(enterpriseId);
		
		return new ResponseEntity<EnterpriseInfoResponseDTO>(responseDTO, HttpStatus.OK);
	}
	
}
