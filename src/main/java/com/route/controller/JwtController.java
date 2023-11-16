package com.route.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.route.config.JwtUtil;
import com.route.entities.JwtRequest;
import com.route.entities.JwtResponse;
import com.route.entities.User;
import com.route.services.UserDetailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "JwtController", description = "API's for JWT token generation")
public class JwtController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailService userDetailService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/generate-token")
	@Operation(summary="token generator", description="JWT token generation by username and password.",method = "POST")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		try {
			authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
		}catch(UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Invalid credentials or user does not exist!");
		}
		//valid
		System.out.println("Validated");
		UserDetails userDetails = this.userDetailService.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	private void authenticate(String username, String password) throws Exception{
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}  catch(BadCredentialsException e) {
			throw new Exception("Invalid Credentials! "+e.getMessage());
		}catch(DisabledException e) {
			throw new Exception("User disabled! "+e.getMessage());
		}
	}
	
	@GetMapping("/current-user")
	@Operation(summary="get current user",description="Get information of the current user logged in.", method = "GET")
	public User getCurrentUser(Principal principal) {
		return (User)this.userDetailService.loadUserByUsername(principal.getName());
	}

}
