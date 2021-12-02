package com.bridgelabz.lmsapi.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.ToString;
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
