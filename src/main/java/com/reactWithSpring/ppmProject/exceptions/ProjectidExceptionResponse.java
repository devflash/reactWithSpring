package com.reactWithSpring.ppmProject.exceptions;

public class ProjectidExceptionResponse {
	
	private String projectIdentifier;

	public String getProjectIdentifier() {
		return projectIdentifier;
	}

	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}

	public ProjectidExceptionResponse(String projectIdentifier) {
		
		this.projectIdentifier = projectIdentifier;
	}
	
	
}
