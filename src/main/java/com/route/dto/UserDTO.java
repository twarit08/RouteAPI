package com.route.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO {

	@NotBlank(message = "role name can't be blank.")
	private String roleName;
	
	@NotBlank(message = "username can't be blank.")
	private String username;
	
	@NotBlank(message = "password can't be blank.")
	@Size(min = 6, message = "enter minimum six character password")
	private String password;
	
	@NotBlank(message = "first name can't be blank.")
	private String firstName;
	
	@NotBlank(message = "last name can't be blank.")
	private String lastName;

	public UserDTO() {
		super();
	}

	public UserDTO(@NotBlank(message = "username can't be blank.") String roleName,
			@NotBlank(message = "username can't be blank.") String username,
			@NotBlank(message = "password can't be blank.") @Size(min = 6, message = "enter minimum six character password") String password,
			@NotBlank(message = "first name can't be blank.") String firstName,
			@NotBlank(message = "last name can't be blank.") String lastName) {
		super();
		this.roleName = roleName;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
