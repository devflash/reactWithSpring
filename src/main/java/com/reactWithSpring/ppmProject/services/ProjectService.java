package com.reactWithSpring.ppmProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reactWithSpring.ppmProject.domain.Project;
import com.reactWithSpring.ppmProject.exceptions.ProjectidException;
import com.reactWithSpring.ppmProject.repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	public Project saveOrUpdateProject(Project project)
	{
		try
		{
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		}
		catch(Exception e)
		{
			throw new ProjectidException("ProjectIdentifier '"+project.getProjectIdentifier().toUpperCase()+"' already exist");
		}
		
	}
	
	public Project getProjectByIdentifier(String projectId)
	{
		Project project=projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if(project==null)
		{
			throw new ProjectidException("project id '"+projectId+"' doesn't exist.");
		}
		return project;
	}
	
	public Iterable<Project> getAllProjects()
	{
		return projectRepository.findAll();
	}
	
	public void deleteProjectById(String projectId)
	{
		Project project=projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if(project==null)
		{
			throw new ProjectidException("project id '"+projectId+"' doesn't exist.");
		}
		projectRepository.delete(project);
	}
	
}
