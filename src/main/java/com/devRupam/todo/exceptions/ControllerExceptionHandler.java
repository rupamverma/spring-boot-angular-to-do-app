package com.devRupam.todo.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ControllerExceptionHandler {

	
	 @ExceptionHandler(ResourceNotFoundException.class)
	  public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
	    ErrorMessage message = new ErrorMessage(
	        HttpStatus.NOT_FOUND.value(),
	        new Date(),
	        ex.getMessage(),
	        request.getDescription(false));
	    
	    return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
	  }

	 
	 protected ResponseEntity<ErrorMessage> handleMissingServletRequestParameter(
	   MissingServletRequestParameterException ex, HttpHeaders headers, 
	   HttpStatus status, WebRequest request) {
	     String error = ex.getParameterName() + " parameter is missing";
	     
	     ErrorMessage message = 
	       new ErrorMessage( HttpStatus.BAD_REQUEST.value(),
	   	        new Date(),
		        ex.getMessage(),
		        request.getDescription(false));
		    
		    return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);	 }
	 
	 
	 
	  @ExceptionHandler(Exception.class)
	  public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
	    ErrorMessage message = new ErrorMessage(
	        HttpStatus.INTERNAL_SERVER_ERROR.value(),
	        new Date(),
	        ex.getMessage(),
	        request.getDescription(false)); 
	    return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
}
