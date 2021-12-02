package com.bridgelabz.lmsapi.dto;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

@Data
@Component
@ToString
public class LoginDTO {
	
	private String passWord;
	private String email;

}
