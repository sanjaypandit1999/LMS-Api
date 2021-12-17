package com.bridgelabz.lmsapi.configuration;

import java.io.InputStream;

import javax.mail.internet.MimeMessage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * purpose to store object in bean
 * 
 * @author Sanjay
 * @version 1.0
 * @since 12/17/2021
 *
 */
@Configuration
public class UserConfiguration {
	
	/**
	 * purpose to encode password and store object in bean
	 * 
	 */
	@Bean
	public PasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder();

	}

	/**
	 * purpose to all JavaMailSender object store in bean
	 * 
	 */
	@Bean
	public JavaMailSender javamailsender() {
		return new JavaMailSender() {

			@Override
			public void send(SimpleMailMessage simpleMessage) throws MailException {
				// TODO Auto-generated method stub

			}

			@Override
			public void send(SimpleMailMessage... simpleMessages) throws MailException {
				// TODO Auto-generated method stub

			}

			@Override
			public MimeMessage createMimeMessage() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public MimeMessage createMimeMessage(InputStream contentStream) throws MailException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void send(MimeMessage mimeMessage) throws MailException {
				// TODO Auto-generated method stub

			}

			@Override
			public void send(MimeMessage... mimeMessages) throws MailException {
				// TODO Auto-generated method stub

			}

			@Override
			public void send(MimeMessagePreparator mimeMessagePreparator) throws MailException {
				// TODO Auto-generated method stub

			}

			@Override
			public void send(MimeMessagePreparator... mimeMessagePreparators) throws MailException {
				// TODO Auto-generated method stub

			}
		};
	}

}
