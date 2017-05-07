package org.debugroom.sample.cassandra.pattern2.domain.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.debugroom.sample.cassandra.pattern2.domain.entity.Affiliation;
import org.debugroom.sample.cassandra.pattern2.domain.entity.AffiliationPK;

public interface AffiliationRepository 
      extends CrudRepository<Affiliation, AffiliationPK>, AffiliationRepositoryCustom{
	
	public List<Affiliation> findByAffiliationpkGroupId(Long groupId);
	
	@Query("select * from affiliation where user_id =?0 allow filtering")
	public List<Affiliation> findByAffiliationpkUserId(Long userId);

}
