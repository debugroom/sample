package org.debugroom.sample.javaee6.service;

import java.util.List;
import org.debugroom.sample.javaee6.domain.model.entity.User;

public interface SampleService {

	public List<User> findUsersService();
	public User findUserService(String companyId, String userId);
	
}
