package com.bridgelabz.lmsapi.util;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.Data;
@SuppressWarnings("serial")
@Component
@Data
public class Email implements Serializable {
	
	String to;
	String from;
	String subject;
	String body;

}
