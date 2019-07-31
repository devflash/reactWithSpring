package com.reactWithSpring.ppmProject.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.reactWithSpring.ppmProject.domain.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long>{

	Project findByProjectIdentifier(String projectId);


	
	
}
