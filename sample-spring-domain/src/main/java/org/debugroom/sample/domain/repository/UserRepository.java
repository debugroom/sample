package org.debugroom.sample.domain.repository;

import java.util.List;
import org.debugroom.sample.domain.model.User;

public interface UserRepository {

	public User findOne(String userId);
	
	public List<User> findAll();
	
	public int getNumberOfUser();
	
	public void saveUser(User user);
	
	public void updateUser(User user);
	
	public void deleteUser(User user);
	
}
