package com.reactWithSpring.ppmProject.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reactWithSpring.ppmProject.domain.Project;
import com.reactWithSpring.ppmProject.services.ErrorMapService;
import com.reactWithSpring.ppmProject.services.ProjectService;

@RestController
@RequestMapping("api/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ErrorMapService errorMapService;
	
	@PostMapping("")
	public ResponseEntity<?> createProject(@Valid @RequestBody Project project,BindingResult result)
	{
		ResponseEntity<?> errorMap=errorMapService.mapErrors(result);
		if(errorMap!=null)
		{
			return errorMap;
		}
		Project project1=projectService.saveOrUpdateProject(project);
		return new ResponseEntity<Project>(project,HttpStatus.CREATED);
	}
	
	@GetMapping("/{projectId}")
	public ResponseEntity<?> getProjectById(@PathVariable String projectId)
	{
		Project project=projectService.getProjectByIdentifier(projectId);
		return new ResponseEntity<Project>(project,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllProject()
	{
		
		return new ResponseEntity<Iterable>(projectService.getAllProjects(),HttpStatus.OK);
	}
	
	@DeleteMapping("/{projectId}")
	public ResponseEntity<?> deleteProjectById(@PathVariable String projectId)
	{
		return new ResponseEntity<String>("Project with ID '"+projectId+" 'Succefully deleted",HttpStatus.OK);
	}
	
	
}
