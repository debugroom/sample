package org.debugroom.sample.spring.jpa.domain.service;

import java.util.List;

import org.debugroom.sample.spring.jpa.domain.entity.Address;
import org.debugroom.sample.spring.jpa.domain.entity.User;

public interface OneToOneSampleService {

	public Address getAddress(User user);
	
	public List<User> getUsersWith(String zipCd);
	
	public List<User> getUsersWithout(String zipCd);
	
	public User addAddress(Address address);

	public void updateAddress(String userId, Address address);
	
	public void deleteAddress(String userId);
	
	public void deleteUser(String userId);
	
}
