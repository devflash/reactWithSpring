package com.reactWithSpring.ppmProject.exceptions;

public class ProjectidExceptionResponse {
	
	private String errorMessage;

	public ProjectidExceptionResponse(String errorMessage) {
		
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	
	
}
