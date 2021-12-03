package com.bridgelabz.lmsapi.service;

import java.util.Date;
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
import com.bridgelabz.lmsapi.util.Email;
import com.bridgelabz.lmsapi.util.JwtToken;

@Service
public class UserService implements IUserService {

	@Autowired
	JwtToken jwtToken;

	@Autowired
	UserRepository userRepository;
	@Autowired
	JavaMailSender javaMailSender;

	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	MailService mailService;

	@Autowired
	Email email;

	public List<User> getUser() {
		return userRepository.findAll();
	}

	public User getUserById(long userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new UserException("User with id " + userId + " does not exist..!"));
	}

	public User createUser(@Valid UserDTO userDTO) {
		User user = new User();
		user.setPassword(passwordEncoder.encode(userDTO.password));
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
			if (passwordEncoder.matches(loginRequest.getPassWord(), email.get().getPassword())) {
				return email.get();
			}
		}
		return null;
	}

	@Override
	public User forgetPassword(ForgotPassDTO forgotPassDTO) throws MessagingException {
		String emailId = forgotPassDTO.getEmail();
		Optional<User> isPresent = userRepository.findByEmail(emailId);
		if (isPresent.isPresent()) {
			email.setTo(emailId);
			email.setFrom("${email}");
			email.setSubject("forgot password link");
			String token = jwtToken.createToken(isPresent.get().getUserId());
			email.setBody(mailService.getLink("http://localhost:8080/reset/", isPresent.get().getUserId()));
			mailService.send(email.getTo(), email.getSubject(), email.getBody());
			System.out.println("Suucc");

			return isPresent.get();

		}
		return null;
	}

	@Override
	public User reset(@Valid String password, String token) {
		// DECODING TOKEN
		Long id = jwtToken.decodeToken(token);
		// CHECKING USER PRESENT OR NOT
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			// SETTING THE NEW PASSWORD AND SAVING INTO THE DATABASE
			user.get().setPassword(passwordEncoder.encode(password));

			user.get().setUpdateDate(new Date(System.currentTimeMillis()));
			return userRepository.save(user.get());
		}
		return null;
	}

}
