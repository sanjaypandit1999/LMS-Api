package com.bridgelabz.lmsapi.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bridgelabz.lmsapi.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


/**
 * purpose to all data store in mongoDB as a document
 * 
 * 
 * @author Sanjay
 * @version 1.0
 * @since 12/17/2021
 */
@Document(collection ="Admin")
@Data
public class User {
	  @Transient
	    public static final String SEQUENCE_NAME = "users_sequence";
	
	@Id
	private long userId;

	private String userName;


	private String email;
	
	
	private String profilePath;

	@JsonIgnore
	private String password;


	private long contactNumber;

	private boolean active = true;

	private Date registerDate;


	private Date updateDate;

	public User(UserDTO userDTO) {

		this.updateUser(userDTO);
	}

	public User() {

	}

	public void updateUser(UserDTO userDTO) {
		this.userName = userDTO.userName;
		this.email = userDTO.email;
		this.profilePath = userDTO.profilePath;
		this.password = userDTO.password;
		this.contactNumber=userDTO.contactNumber;
		this.updateDate = new Date(System.currentTimeMillis());
	}

	public void createUser(UserDTO userDTO) {
		this.userName = userDTO.userName;
		this.email = userDTO.email;
		this.profilePath = userDTO.profilePath;
		this.contactNumber=userDTO.contactNumber;
		this.registerDate = new Date(System.currentTimeMillis());

	}

}
