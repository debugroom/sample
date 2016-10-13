package org.debugroom.sample.spring.jpa.domain.service;

import java.util.List;

import org.debugroom.sample.spring.jpa.domain.entity.User;
import org.debugroom.sample.spring.jpa.domain.entity.Address;
import org.debugroom.sample.spring.jpa.domain.entity.Email;
import org.debugroom.sample.spring.jpa.domain.entity.Group;

public interface SimpleAccessService {

	public List<User> getUsers();
	
	public List<Address> getAddressList();
	
	public List<Email> getEmails();
	
	public List<Group> getGroups();
	
	public User getUser(String userId);
	
	public Address getAddress(User user);
	
	public List<Email> getEmails(User user);
	
	public Group getGroup(String groupName);

}
