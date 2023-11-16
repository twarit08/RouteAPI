package com.route.services;

import java.util.Set;

import com.route.entities.User;
import com.route.entities.UserRole;

public interface UserService {
	
	public User registerUser(User user, Set<UserRole> userRole) throws Exception;
	
	public User getUserByUsername(String username);

}
