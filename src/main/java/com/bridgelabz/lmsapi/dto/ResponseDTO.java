package com.bridgelabz.lmsapi.dto;

import lombok.Data;

/**
 * purpose to response message from server
 * 
 * @author Sanjay
 * @version 1.0
 * @since 12/17/2021
 */
@Data
public class ResponseDTO {
	
	private String message;
	private Object data;

	public ResponseDTO(String message, Object data) {
		this.message = message;
		this.data = data;
	}

}
