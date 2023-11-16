package com.route.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.route.dto.UserDTO;
import com.route.entities.Role;
import com.route.entities.User;
import com.route.entities.UserRole;
import com.route.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/routes/")
@Tag(name="UserController", description = "API's for user signup")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")
	@Operation(summary="user sign up", description="user registration for accessing the routes.")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO userReg){
		
		User user = new User();
		user.setFirstName(userReg.getFirstName());
		user.setLastName(userReg.getLastName());
		user.setUsername(userReg.getUsername());
		user.setPassword(userReg.getPassword());
		
		Role role = new Role();
		
		UserRole userRole = new UserRole();
		
		Set<UserRole> userRoles = new HashSet<>();
		
		if(userReg.getRoleName().equals("ADMIN")) {
			role.setRoleId(101);
			role.setRoleName("ADMIN");
		}else if(userReg.getRoleName().equals("CUSTOMER")) {
			role.setRoleId(102);
			role.setRoleName("CUSTOMER");
		}
		userRole.setUser(user);
		userRole.setRole(role);
		
		userRoles.add(userRole);
		
		try {
			this.userService.registerUser(user, userRoles);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
