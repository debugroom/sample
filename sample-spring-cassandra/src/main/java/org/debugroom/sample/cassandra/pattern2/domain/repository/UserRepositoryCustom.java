package org.debugroom.sample.cassandra.pattern2.domain.repository;

import java.util.Map;
import java.util.List;

import org.debugroom.sample.cassandra.pattern2.domain.entity.User;

public interface UserRepositoryCustom {

	public Map<Long, User> findAllForMap();
	
	public Map<String, List<User>> findAllForMappedListByZipCd();
	
	public Map<String, User> findAllForMappByEmail();
	
}
