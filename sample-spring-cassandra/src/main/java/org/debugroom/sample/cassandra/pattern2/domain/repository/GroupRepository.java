package org.debugroom.sample.cassandra.pattern2.domain.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.debugroom.sample.cassandra.pattern2.domain.entity.Group;

public interface GroupRepository extends CrudRepository<Group, Long>{
	
	// Use query adding allow filtering option or secondary index or materialized view.
	@Query("select * from group where group_name =?0 allow filtering")
	public List<Group> findByGroupName(String groupName);

	public List<Group> findByGroupIdIn(List<Long> groupIds);
	
}
