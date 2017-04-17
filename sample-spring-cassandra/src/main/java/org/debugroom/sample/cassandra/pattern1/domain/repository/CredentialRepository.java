package org.debugroom.sample.cassandra.pattern1.domain.repository;

import java.util.List;

import org.debugroom.sample.cassandra.pattern1.domain.entity.Credential;
import org.debugroom.sample.cassandra.pattern1.domain.entity.CredentialPK;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CredentialRepository extends CrudRepository<Credential, CredentialPK>{
	
	@Query("select * from credential where user_id = ?0 allow filtering")
	public List<Credential> findByUserId(Long userId);

}
