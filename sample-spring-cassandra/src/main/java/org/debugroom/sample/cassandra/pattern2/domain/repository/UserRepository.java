package org.debugroom.sample.cassandra.pattern2.domain.repository;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

import org.debugroom.sample.cassandra.pattern2.domain.entity.User;

public interface UserRepository extends CrudRepository<User, Long>, UserRepositoryCustom{
	
	@Query("select * from users where login_id = ?0")
//	@Query("select * from users_by_login_id where login_id = ?0")
	public User findUserByLoginId(String loginId);

	public List<User> findByUserIdIn(List<Long> userIds);

}
