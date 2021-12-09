package com.bridgelabz.lmsapi.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgelabz.lmsapi.service.MailService;

@Component
public class MessageLIstenerImpl implements IMessageListener {
	
	@Autowired
	private MailService  mailsender;

	@SuppressWarnings("static-access")
	@Override
	public void onMessage(Email email) {
		
		System.out.println(email);
		System.out.println("to : "+email.to);
		System.out.println("from : "+email.from);
		System.out.println("body:"+email.body);
		System.out.println("subject:"+email.subject);
		//SENDING MESSAGE FROM JMS GETTING FROM RABBITMQ ONE BY ONE
		mailsender.send(email.to, email.subject, email.body);
		System.out.println("****************************************");
		

	  
	  System.out.println("Message Received:" +email.toString());

	  System.out.println(new Date());
		
	}

}
