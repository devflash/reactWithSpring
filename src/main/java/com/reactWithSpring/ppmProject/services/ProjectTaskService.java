package com.reactWithSpring.ppmProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.reactWithSpring.ppmProject.domain.Backlog;
import com.reactWithSpring.ppmProject.domain.ProjectTask;
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
	
}
