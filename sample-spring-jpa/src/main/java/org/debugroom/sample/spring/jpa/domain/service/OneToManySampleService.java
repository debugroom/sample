package org.debugroom.sample.spring.jpa.domain.service;

import java.util.Set;

import org.debugroom.sample.spring.jpa.domain.entity.Email;
import org.debugroom.sample.spring.jpa.domain.entity.User;

public interface OneToManySampleService {

	Set<Email> getEmails(User user);
	
	User getUser(String email);
	
	User addEmail(User user, String email);
	
	User addUser(User user, String email);
	
	User updateEmail(User user, Email email);
	
	User deleteEmail(User user, Email email);
	
	User deleteEmails(User user);
	
	void deleteUser(User user);

}
