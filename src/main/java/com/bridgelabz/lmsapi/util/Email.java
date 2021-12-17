package com.bridgelabz.lmsapi.util;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.Data;

/**
 *create pojo class of email
 * 
 * @author Sanjay
 * @version 1.0
 * @since 12/17/2021
 */
@SuppressWarnings("serial")
@Component
@Data
public class Email implements Serializable {
	
	String to;
	String from;
	String subject;
	String body;

}
