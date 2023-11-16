package com.route.config;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	// custom exception
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetail> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
		ErrorDetail errorDetail = new ErrorDetail(new Date(), exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetail,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserExistException.class)
	public ResponseEntity<ErrorDetail> handleUserExistException(UserExistException exception, WebRequest webRequest){
		ErrorDetail errorDetail = new ErrorDetail(new Date(), exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetail, HttpStatus.CONFLICT);
	}
	
	// global exception
	public ResponseEntity<ErrorDetail> handleGlobalException(Exception exception, WebRequest webRequest){
		ErrorDetail errorDetail = new ErrorDetail(new Date(), exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetail,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
