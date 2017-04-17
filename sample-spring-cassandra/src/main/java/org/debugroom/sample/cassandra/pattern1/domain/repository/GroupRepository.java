package org.debugroom.sample.cassandra.pattern1.domain.repository;

import java.util.List;

import org.debugroom.sample.cassandra.pattern1.domain.entity.Group;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<Group, Long>{

	// Use query adding allow filtering option or secondary index or materialized view.
	@Query("select * from group where group_name = ?0 allow filtering")
	public List<Group> findByGroupName(String groupName);
	
}
