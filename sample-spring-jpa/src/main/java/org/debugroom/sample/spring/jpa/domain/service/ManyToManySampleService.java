package org.debugroom.sample.spring.jpa.domain.service;

import java.util.List;

import org.debugroom.sample.spring.jpa.domain.entity.Group;
import org.debugroom.sample.spring.jpa.domain.entity.User;

public interface ManyToManySampleService {

	List<Group> getGroups(User user);
	
	List<User> getUsersWith(Group group);
	
	List<User> getUsersWithout(Group group);
	
	User addUserTo(Group group, User user);
	
	User deleteUserFrom(Group group, User user);
	
	Group deleteGroup(Group group);
	
	User deleteUser(User user);
	
}
