package com.bridgelabz.lmsapi.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgelabz.lmsapi.dto.ResponseDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * purpose to handle Exception
 * 
 * @author Sanjay
 * @version 1.0
 * @since 12/17/2021
 *
 */
@ControllerAdvice
@Slf4j
public class UserExceptionHandler {
	private static final String message = "Exception While Processing REST Request";

	/**
	 * purpose to handle message readable exception
	 * 
	 * @param HttpMessageNotReadableException object
	 * @return Bad request
	 *
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ResponseDTO> handelHttpMessageNotReadableException(
			HttpMessageNotReadableException exception) {
		log.error("Invalid Date Format", exception);
		ResponseDTO responseDTO = new ResponseDTO(message, "Should have date in dd MM yyyy format");
		return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
	}

	/**
	 * purpose to handle validation exception
	 * 
	 * @param MethodArgumentNotValidException object
	 * @return Bad request
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseDTO> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException exception) {
		List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
		List<String> errorMessage = errorList.stream().map(objerr -> objerr.getDefaultMessage())
				.collect(Collectors.toList());

		ResponseDTO responseDTO = new ResponseDTO(message, errorMessage);
		return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
	}

	/**
	 * purpose to handle custom exception
	 * 
	 * @param UserException object
	 * @return Bad request
	 */
	@ExceptionHandler(UserException.class)
	public ResponseEntity<ResponseDTO> handleUserException(UserException exception) {
		ResponseDTO responseDTO = new ResponseDTO(message, exception.getMessage());
		return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
	}

}
