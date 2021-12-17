package com.bridgelabz.lmsapi.dto;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

/**
 * purpose to pass login  data  client to server
 * 
 * @author Sanjay
 * @version 1.0
 * @since 12/17/2021
 */
@Data
@Component
@ToString
public class LoginDTO {
	
	private String passWord;
	private String email;

}
