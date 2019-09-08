package com.reactWithSpring.ppmProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.reactWithSpring.ppmProject.domain.Backlog;
import com.reactWithSpring.ppmProject.domain.ProjectTask;
import com.reactWithSpring.ppmProject.exceptions.ProjectidException;
import com.reactWithSpring.ppmProject.repositories.BacklogRepository;
import com.reactWithSpring.ppmProject.repositories.ProjectTaskRepository;

@Service
public class ProjectTaskService {
	
	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	
	@Autowired
	private BacklogRepository backlogRepository;
	
	public ProjectTask addProjectTaskToProject(ProjectTask projectTask,String projectIdentifier)
	{
		Backlog backlog=backlogRepository.findByProjectIdentifier(projectIdentifier);
		if(backlog==null)
		{
			throw new ProjectidException("project id "+ projectIdentifier+" doesn't exist");
		}
		projectTask.setBacklog(backlog);
		Integer backlogSequence=backlog.getPTSequence();
		backlogSequence++;
		backlog.setPTSequence(backlogSequence);
		projectTask.setProjectSequence(projectIdentifier+"-"+backlogSequence);
		projectTask.setProjectIdentifier(projectIdentifier);
		if(projectTask.getPriority()==null)
		{
			projectTask.setPriority(3);
		}
		if(projectTask.getStatus()=="" || projectTask.getStatus()==null)
		{
			projectTask.setStatus("TO-DO");
		}
		return projectTaskRepository.save(projectTask);
	}

	public Iterable<ProjectTask> getProjectTasksById(String projectIdentifier)
	{
		return projectTaskRepository.findByProjectIdentifierOrderByPriority(projectIdentifier);
	}
	
	public ProjectTask findProjectTaskBySequence(String projectIdentifier,String projectTaskSequence)
	{
		Backlog backlog=backlogRepository.findByProjectIdentifier(projectIdentifier);
		if(backlog==null)
		{
			throw new ProjectidException("Project with id "+projectIdentifier+" doesn't exist");
		}
		ProjectTask projectTask=projectTaskRepository.findByProjectSequence(projectTaskSequence);
		if(projectTask==null)
		{
			throw new ProjectidException("Project task doesn't exist");
		}
		if(!projectTask.getProjectIdentifier().equals(backlog.getProjectIdentifier()))
		{
			throw new ProjectidException("Project task doesn't exist");
		}
		return projectTask;
	}
	
	
	
}
