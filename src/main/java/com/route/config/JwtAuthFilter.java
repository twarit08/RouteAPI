package com.route.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.route.services.UserDetailService;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{
	
	@Autowired
	private UserDetailService userDetailService;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;
		
		if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")) {
			
			jwtToken = requestTokenHeader.substring(7);
			
			try {
				username = this.jwtUtil.extractUsername(jwtToken);
			}catch(ExpiredJwtException e) {
				e.printStackTrace();
				System.out.println("Token expired!");
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}/*else {
			System.out.println("Invalid token! Not start's from Bearer string!");
		}*/
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
			try {
				final UserDetails userDetails = this.userDetailService.loadUserByUsername(username);
				if(this.jwtUtil.validateToken(jwtToken, userDetails)) {
					UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
					userToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(userToken);
				}else {
					System.out.println("Token is invalid! Please generate a new token!");
				}
				
			}catch(UsernameNotFoundException e) {
				e.printStackTrace();
			}
			
		}/*else {
			System.out.println("Something went wrong! Please try again!");
		}*/
		
		filterChain.doFilter(request, response);
		
	}
	
}
