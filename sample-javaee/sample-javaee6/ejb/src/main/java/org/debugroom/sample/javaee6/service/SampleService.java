package org.debugroom.sample.javaee6.service;

import org.debugroom.sample.javaee6.domain.model.entity.User;

public interface SampleService {

	public User findUserService(String companyId, String userId);
	
}
