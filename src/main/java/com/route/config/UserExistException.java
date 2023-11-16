package com.route.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT)
public class UserExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String user;
	private String fieldName;
	
	public UserExistException(String userName, String user, String fieldName) {
		super(String.format("%s already exist with %s : %s", userName, user, fieldName));
		this.userName = userName;
		this.user = user;
		this.fieldName = fieldName;
	}

	public String getUserName() {
		return userName;
	}

	public String getUser() {
		return user;
	}

	public String getFieldName() {
		return fieldName;
	}

}
