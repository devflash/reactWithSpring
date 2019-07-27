package com.reactWithSpring.ppmProject.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.reactWithSpring.ppmProject.domain.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long>{

	@Override
	default Iterable<Project> findAllById(Iterable<Long> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
