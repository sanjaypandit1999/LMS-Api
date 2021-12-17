package com.bridgelabz.lmsapi.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.ToString;


/**
 * purpose to pass data with multiple attributes in one shot from client to server
 * 
 * @author Sanjay
 * @version 1.0
 * @since 12/17/2021
 */
@ToString
public class UserDTO {
		@Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Employee Name is invalid")
		@NotEmpty
		public String userName;
		@NotBlank(message = "Select a Profile Path")
		public String profilePath;
	    @Email
	    @NotEmpty
	    public String email;
	    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]*.{8,}$")
	    public String password;
	    @NotNull
	    public long contactNumber;
}
