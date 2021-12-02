package com.bridgelabz.lmsapi.service;

import java.util.List;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.bridgelabz.lmsapi.dto.ForgotPassDTO;
import com.bridgelabz.lmsapi.dto.LoginDTO;
import com.bridgelabz.lmsapi.dto.UserDTO;
import com.bridgelabz.lmsapi.model.User;

@Service
public interface IUserService {

	List<User> getUser();

	User updateUser(long userId, @Valid UserDTO userDTO);

	void deleteUser(long userId);

	String deleteAllUserData();

	User createUser(@Valid UserDTO usertDTO);

	User getUserById(long userId);

	User loginRequest(@Valid LoginDTO loginRequest);

}
