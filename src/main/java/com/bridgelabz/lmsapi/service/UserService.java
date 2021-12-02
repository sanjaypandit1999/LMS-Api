package com.bridgelabz.lmsapi.service;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.lmsapi.dto.ForgotPassDTO;
import com.bridgelabz.lmsapi.dto.LoginDTO;
import com.bridgelabz.lmsapi.dto.UserDTO;
import com.bridgelabz.lmsapi.exception.UserException;
import com.bridgelabz.lmsapi.model.User;
import com.bridgelabz.lmsapi.repository.UserRepository;
import com.bridgelabz.lmsapi.util.JwtToken;

@Service
public class UserService implements IUserService {

	@Autowired
	JwtToken jwtToken;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	JavaMailSender javaMailSender;
//	@Autowired
//	PasswordEncoder passwordEncoder;

	public List<User> getUser() {
		return userRepository.findAll();
	}

	public User getUserById(long userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new UserException("User with id " + userId + " does not exist..!"));
	}

	public User createUser(@Valid UserDTO userDTO) {
		User user = new User();
		user.createUser(userDTO);
		return userRepository.save(user);
	}

	public User updateUser(long userId, @Valid UserDTO userDTO) {
		User user = this.getUserById(userId);
		user.updateUser(userDTO);
		return userRepository.save(user);
	}

	public void deleteUser(long userId) {
		User user = this.getUserById(userId);
		userRepository.delete(user);

	}

	public String deleteAllUserData() {
		userRepository.deleteAll();
		return "Successfully deleted all the User";
	}

	@Override
	public User loginRequest(@Valid LoginDTO loginRequest) {
		Optional<User> email = userRepository.findByEmail(loginRequest.getEmail());
		if (email.isPresent()) {
			System.out.println(email.get().toString());
			if (loginRequest.getPassWord().equals(email.get().getPassword())) {
				return email.get();
			}
		}
		return null;
	}

}
