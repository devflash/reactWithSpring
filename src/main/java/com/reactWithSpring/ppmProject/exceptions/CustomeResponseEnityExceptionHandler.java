package com.reactWithSpring.ppmProject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomeResponseEnityExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleProjectIdException(Exception ex,WebRequest req)
	{
			ProjectidExceptionResponse exceptionResponse=new ProjectidExceptionResponse(ex.getMessage());
			return new ResponseEntity(exceptionResponse,HttpStatus.BAD_REQUEST);	
	
	}
	

}
