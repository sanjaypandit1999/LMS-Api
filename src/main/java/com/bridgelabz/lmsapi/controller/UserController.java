package com.bridgelabz.lmsapi.controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.lmsapi.dto.ForgotPassDTO;
import com.bridgelabz.lmsapi.dto.LoginDTO;
import com.bridgelabz.lmsapi.dto.ResponseDTO;
import com.bridgelabz.lmsapi.dto.UserDTO;
import com.bridgelabz.lmsapi.model.User;
import com.bridgelabz.lmsapi.service.IUserService;
import com.bridgelabz.lmsapi.util.JwtToken;

import lombok.extern.slf4j.Slf4j;


/**
 * purpose to provide REST API call
 * 
 * @author Sanjay
 * @version1.0
 * @since 12/17/2021
 * 
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

	@Autowired
	JwtToken jwtToken;

	@Autowired
	private IUserService iUserService;

	 /**
     * Retrieve all data from database
     *
     * @return UserResponse all user datails
     */
	@RequestMapping(value = { "", "/", "/get" })
	public ResponseEntity<ResponseDTO> getUserData() {
		List<User> userList = iUserService.getUser();
		ResponseDTO response = new ResponseDTO("Get call success", userList);
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}

	 /**
     * Take user id to get user details from database database
     *
     * @param long userId
     * @return response is user data
     */
	@GetMapping("/get/{userId}")
	public ResponseEntity<ResponseDTO> getUserData(@PathVariable("userId") long userId) {
		User user = iUserService.getUserById(userId);
		ResponseDTO response = new ResponseDTO("Get call success for id", user);
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

	}
	
	 /**
     * Take user details to register in database
     *
     * @param userDTO
     * @return UserResponse as JWTToken
     */
	@PostMapping("/register")
	public ResponseEntity<ResponseDTO> addUserData(@Valid @RequestBody UserDTO usertDTO) {
		User user = iUserService.createUser(usertDTO);
		log.debug("LMS user DTO: " +usertDTO.toString());
		ResponseDTO response = new ResponseDTO("user Data Added Successfully",
				jwtToken.createToken(user.getUserId()));
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

	}

	 /**
     * Take user details to update in database
     *
     * @param long userId and UserDTO body 
     * @return UserResponse as JWTToken
     */
	@PutMapping("/update/{userId}")
	public ResponseEntity<ResponseDTO> updateUserData(@PathVariable("userId") long userId,
			@Valid @RequestBody UserDTO userDTO) {
		User user = iUserService.updateUser(userId, userDTO);
		ResponseDTO response = new ResponseDTO("Updated Contact Data ", jwtToken.createToken(user.getUserId()));
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

	}

	 /**
     * Take user details to register in database
     *
     * @param long userId
     * @return id is deleted 
     */
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<ResponseDTO> deleteUserData(@PathVariable("userId") long userId) {
		iUserService.deleteUser(userId);
		ResponseDTO response = new ResponseDTO("Delete call success for id ", "deleted id:" + userId);
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

	}

	 /**
     * Delete all user details in database
     *
     * @return Delete all user details.
     */
	@DeleteMapping("/deleteall")
	public ResponseEntity<ResponseDTO> deleteAllUserData() {
		String message = iUserService.deleteAllUserData();
		ResponseDTO respDTO = new ResponseDTO("Deleteall:", message);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
	
    /**
	 * Takes emailId and password to login in application
	 *
	 * @param loginRequest
	 * @return LoginResponse
	 */
	@PostMapping("/login")
	public ResponseEntity<ResponseDTO> loginUser(@Valid @RequestBody LoginDTO loginRequest) {
		User user = iUserService.loginRequest(loginRequest);
		ResponseDTO response = new ResponseDTO("Login Successfull", jwtToken.createToken(user.getUserId()));
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}

    /**
	 * Take emailId  to forget password
	 *
	 * @param forgetPass
	 * @return ForgotPassword link sent successfully to emailId
	 */
	@GetMapping("/forgotpassword")
	public ResponseEntity<ResponseDTO> ForgetPassword(@Valid @RequestBody ForgotPassDTO forgotPassDTO)
			throws MessagingException {
		User user = iUserService.forgetPassword(forgotPassDTO);
		ResponseDTO response = new ResponseDTO("Forgot Password Successfull", user);
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}
	
    /**
	 * Takes token and password to reset password
	 *
	 * @param password and token
	 * @return passWord reset successfully
	 */
	@PostMapping("/reset/{token}")
	ResponseEntity<ResponseDTO> resetpass(@Valid @RequestParam String password, @PathVariable String token) {
		User user = iUserService.reset(password, token);
		ResponseDTO response = new ResponseDTO(" Password Reset Successfully", user);
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}
	@GetMapping("/verify")
	ResponseEntity<Boolean> verifyUser(@RequestParam String token) {
		boolean user = iUserService.verifyUser( token);
		return new ResponseEntity<Boolean>(user, HttpStatus.OK);
	}
}
