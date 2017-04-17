package org.debugroom.sample.cassandra.pattern1.domain.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.debugroom.sample.cassandra.pattern1.domain.entity.User;

public interface UserRepository extends CrudRepository<User, Long>, UserRepositoryCustom{

	public List<User> findByUserIdIn(List<Long> userIds);
	
	public List<User> findByUserIdNotIn(List<Long> userIds);

}
