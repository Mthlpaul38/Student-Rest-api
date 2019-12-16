package com.example.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomException extends ResponseEntityExceptionHandler {

	
	  @ExceptionHandler(Exception.class) public final ResponseEntity<Object>
	  handleAllExceptions(Exception ex,WebRequest request) {
	  
	  ExceptionResponse exceptionResponse=new ExceptionResponse(ex.getMessage(),
	  request.getDescription(false));
	  return new ResponseEntity(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	  
	  }
	 
	/*
	 * @ExceptionHandler(UsernotFoundException.class) public final
	 * ResponseEntity<Object> Usernotfound(Exception ex,WebRequest request) {
	 * 
	 * ExceptionResponse exceptionResponse=new ExceptionResponse(ex.getMessage(),
	 * request.getDescription(false)); return new
	 * ResponseEntity(exceptionResponse,HttpStatus.BAD_REQUEST);
	 * 
	 * }
	 */

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		
		ExceptionResponse exceptionResponse=new ExceptionResponse("Validation failed", ex.getBindingResult().toString());
		return new ResponseEntity<Object>(exceptionResponse,HttpStatus.BAD_REQUEST);
	}

	
}
