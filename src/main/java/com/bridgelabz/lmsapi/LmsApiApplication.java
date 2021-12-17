package com.bridgelabz.lmsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@ComponentScan(basePackages = { "com.*"})
public class LmsApiApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(LmsApiApplication.class, args);
		log.info("LMS App Started in {} Environment", context.getEnvironment().getProperty("environment"));
		log.info("lms DB User is {}", context.getEnvironment().getProperty("spring.datasource.username"));
	}

}
