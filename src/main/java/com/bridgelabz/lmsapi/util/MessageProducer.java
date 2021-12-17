package com.bridgelabz.lmsapi.util;

import java.util.Date;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgelabz.lmsapi.configuration.RabbitMqConfig;

/**
 *purpose to send Message to queue 
 * 
 * @author Sanjay
 * @version 1.0
 * @since 12/17/2021
 */
@Component
public class MessageProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	/**
	 *purpose to send message  server to queue
	 * 
	 */
	public void sendMessage(Email email) {

		System.out.println(new Date());
		System.out.println();
		rabbitTemplate.convertAndSend(RabbitMqConfig.ROUTING_KEY, email);
		System.out.println("Is Producer returned ::: " + rabbitTemplate.isReturnListener());
		System.out.println(email);
		System.out.println(new Date());
	}

}
