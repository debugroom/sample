package org.debugroom.sample.spring.jpa.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.debugroom.sample.spring.jpa.domain.entity.Group;

public interface GroupRepository extends JpaRepository<Group, String>,
											JpaSpecificationExecutor<Group>{
	
	public Group findByGroupName(String groupName);
	
}
