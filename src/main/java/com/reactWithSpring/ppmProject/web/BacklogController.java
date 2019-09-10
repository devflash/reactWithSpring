package com.reactWithSpring.ppmProject.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reactWithSpring.ppmProject.domain.ProjectTask;
import com.reactWithSpring.ppmProject.services.ErrorMapService;
import com.reactWithSpring.ppmProject.services.ProjectTaskService;

@RestController
@RequestMapping("api/backlog")
@CrossOrigin
public class BacklogController {
	@Autowired
	private ProjectTaskService projectTaskService;
	@Autowired
	private ErrorMapService errorMapService;
	@PostMapping("/{projectIdentifier}")
	public ResponseEntity<?> addProjectTaskToProject(@Valid @RequestBody ProjectTask projectTask,BindingResult result,@PathVariable String projectIdentifier){
		ResponseEntity<?> errorMap= errorMapService.mapErrors(result);
	
		if(errorMap!=null)
		{
			return errorMap;
		}
		ProjectTask projectTask1=projectTaskService.addProjectTaskToProject(projectTask, projectIdentifier);
		return new ResponseEntity<ProjectTask>(projectTask1,HttpStatus.CREATED);
	}
	
	@GetMapping("/{projectIdentifier}")
	public ResponseEntity<?> getProjectTaskById(@PathVariable String projectIdentifier)
	{
		Iterable<ProjectTask> projectList=projectTaskService.getProjectTasksById(projectIdentifier);
		return new ResponseEntity<Iterable>(projectList,HttpStatus.OK);
	}
	
	@GetMapping("/{projectIdentifier}/{projectTaskSequence}")
	public ResponseEntity<?> findProjectTaskBySequence(@PathVariable String projectIdentifier,@PathVariable String projectTaskSequence)
	{
		ProjectTask projectTask=projectTaskService.findProjectTaskBySequence(projectIdentifier,projectTaskSequence);
		return new ResponseEntity<ProjectTask>(projectTask,HttpStatus.OK);
	}
	
	@DeleteMapping("/{projectIdentifier}/{projectTaskSequence}")
	public ResponseEntity<?> deleteProjectTaskBySequence(@PathVariable String projectIdentifier,@PathVariable String projectTaskSequence)
	{
		projectTaskService.deleteProjectTaskBySequence(projectIdentifier,projectTaskSequence);
		return new ResponseEntity<String>("Project Task with ID '"+projectTaskSequence+" 'Succefully deleted",HttpStatus.OK);
	}
	
	
	
}
