package org.debugroom.sample.javaee6.domain.repository;

import java.util.List;
import org.debugroom.sample.javaee6.domain.model.entity.User;
import org.debugroom.sample.javaee6.domain.model.entity.UserPK;

public interface UserRepository {

	public User findOne(UserPK userPK);
	public List<User> findAll();
	
}
