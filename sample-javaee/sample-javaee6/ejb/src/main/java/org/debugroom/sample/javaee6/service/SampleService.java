package org.debugroom.sample.javaee6.service;

import java.util.List;
import org.debugroom.sample.javaee6.domain.model.entity.User;
import org.debugroom.sample.javaee6.domain.model.entity.Address;

public interface SampleService {

	public List<User> findUsersService();
	public List<Address> findAddressService(String companyId, String userId);
	public User findUserService(String companyId, String userId);
	
}
