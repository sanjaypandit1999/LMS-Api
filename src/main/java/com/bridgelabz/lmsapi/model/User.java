package com.bridgelabz.lmsapi.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import com.bridgelabz.lmsapi.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "employeepayroll_db")
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;

	@Column(name = "username")
	private String userName;

	@Column(name = "email")
	private String email;
	
	@Column(name = "profilePath")
	private String profilePath;

	@JsonIgnore
	@Column(name = "Password")
	private String password;

	@Column(name = "contact_number")
	private long contactNumber;

	private boolean active = true;

	@Temporal(TemporalType.TIMESTAMP)
	private Date registerDate;

	@Temporal(TemporalType.TIMESTAMP)
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
