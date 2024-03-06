package com.flab.jobgo.common.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.flab.jobgo.common.dto.ResponseDTO;

// Controller의 ResponseEntity관련 중복코드를 제거하고 재사용을 위한 클래스
public class ResponseGenerateUtil {

	public static ResponseEntity<ResponseDTO> generateResponse(String message, HttpStatus status){
		ResponseDTO responseDTO = new ResponseDTO(message, status.value());
		return new ResponseEntity<ResponseDTO>(responseDTO, status);
	}
}
