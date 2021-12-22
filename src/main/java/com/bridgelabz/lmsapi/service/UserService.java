package com.bridgelabz.lmsapi.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.bridgelabz.lmsapi.util.MessageProducer;

/**
 * purpose to write business logic implements by IUserService
 * 
 * @author Sanjay
 * @version 1.0
 * @since 12/17/2021
 */
@Service
public class UserService implements IUserService {

	/**
	 * Inject object by using @Autowired annotation
	 * 
	 */
	@Autowired
	JwtToken jwtToken;

	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	MailService mailService;
	@Autowired
	Email email;
	@Autowired
	private MessageProducer messageproducer;
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;


	/**
	 * purpose to retrieve all data from database
	 * 
	 * @return all data from DB
	 */
	public List<User> getUser() {
		return userRepository.findAll();
	}


	/**
	 * purpose to find individual user
	 * 
	 * @param userId
	 * @return user data
	 */
	public User getUserById(long userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new UserException("User with id " + userId + " does not exist..!"));
	}


	/**
	 * purpose to add register data in DB
	 * 
	 * @param userDTO
	 * @return register data
	 */
	public User createUser(@Valid UserDTO userDTO) {
		User user = new User();
		user.setUserId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
		user.setPassword(passwordEncoder.encode(userDTO.password));
		user.createUser(userDTO);
		return userRepository.save(user);
	}


	/**
	 * purpose to update  individual user
	 * 
	 * @param userId and body userDTO
	 * @return updated user data
	 */
	public User updateUser(long userId, @Valid UserDTO userDTO) {
		User user = this.getUserById(userId);
		user.updateUser(userDTO);
		return userRepository.save(user);
	}


	/**
	 * purpose to delete individual user
	 * 
	 * @param userId
	 * @return delete user data
	 */
	public void deleteUser(long userId) {
		User user = this.getUserById(userId);
		userRepository.delete(user);

	}


	/**
	 * purpose to delete all user data in database
	 * 
	 * @return Successfully deleted all the User
	 */
	public String deleteAllUserData() {
		userRepository.deleteAll();
		return "Successfully deleted all the User";
	}

	/**
	 * purpose to login in server using exist email and password
	 * 
	 * @param body loginRequest
	 * @return login Successful
	 */
	@Override
	public User loginRequest(@Valid LoginDTO loginRequest) {
		Optional<User> email = userRepository.findByEmail(loginRequest.getEmail());
		if (email.isPresent()) {
			System.out.println(email.get().toString());
			if (passwordEncoder.matches(loginRequest.getPassWord(), email.get().getPassword())) {
				return email.get();
			}
		}
		throw new UserException("Email not found");
	}


	/**
	 * purpose to forget password in exist user
	 * 
	 * @param body as a email
	 * @return forgot Successfully
	 */
	@Override
	public User forgetPassword(ForgotPassDTO forgotPassDTO) throws MessagingException {
		String emailId = forgotPassDTO.getEmail();
		Optional<User> isPresent = userRepository.findByEmail(emailId);
		if (isPresent.isPresent()) {
			email.setTo(emailId);
			email.setFrom(System.getenv("email"));
			email.setSubject("forgot password link");
			email.setBody(mailService.getLink("Hii  " +isPresent.get().getUserName()+ " Reset your password -"+" http://localhost:8081/reset/", isPresent.get().getUserId()));
			messageproducer.sendMessage(email);

			return isPresent.get();

		}
		throw new UserException("Email not found");
	}


	/**
	 * purpose to reset password 
	 * 
	 * @param new password and jwtToken
	 * @return save updated password in DB
	 */
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
		throw new UserException("User not found");
	}


	@Override
	public boolean verifyUser(String token) {
		long id = jwtToken.decodeToken(token);
		Optional<User> userPresent = userRepository.findById(id);
		if(userPresent.isPresent()) {
			userPresent.get().setActive(true);
			userRepository.save(userPresent.get());

			return true;
		}else {
			return false;
		}
	}

}
