package com.reactWithSpring.ppmProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reactWithSpring.ppmProject.domain.Project;
import com.reactWithSpring.ppmProject.repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	public Project saveOrUpdateProject(Project project)
	{
		return projectRepository.save(project);
	}
	
}
