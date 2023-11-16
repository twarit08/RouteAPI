package com.route.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.route.config.UserExistException;
import com.route.entities.User;
import com.route.entities.UserRole;
import com.route.repo.RoleRepo;
import com.route.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public User registerUser(User user, Set<UserRole> userRole) throws Exception {
		User userReg = this.userRepo.findByUsername(user.getUsername());
		if(userReg!=null) {
			throw new UserExistException("Username", "user", userReg.getUsername());
		}else {
			for(UserRole ur: userRole) {
				this.roleRepo.save(ur.getRole());
			}
			user.getUserRole().addAll(userRole);
			user.setPassword(this.passwordEncoder.encode(user.getPassword()));
			userReg = this.userRepo.save(user);
		}
		
		return userReg;
	}

	@Override
	public User getUserByUsername(String username) {
		User user = this.userRepo.findByUsername(username);
		return user;
	}

}
