package org.debugroom.sample.cassandra.pattern1.domain.repository;

import java.util.Map;

import org.debugroom.sample.cassandra.pattern1.domain.entity.User;

public interface UserRepositoryCustom {

	public Map<Long, User> findAllForMap();

	public User addAddress(User user, String zipCd, String address);
	
}
