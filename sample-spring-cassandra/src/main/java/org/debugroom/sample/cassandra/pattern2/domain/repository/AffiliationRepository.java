package org.debugroom.sample.cassandra.pattern2.domain.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.debugroom.sample.cassandra.pattern2.domain.entity.Affiliation;
import org.debugroom.sample.cassandra.pattern2.domain.entity.AffiliationPK;

public interface AffiliationRepository extends CrudRepository<Affiliation, AffiliationPK>{
	
	public List<Affiliation> findByAffiliationKeyGroupId(Long groupId);
	
}
