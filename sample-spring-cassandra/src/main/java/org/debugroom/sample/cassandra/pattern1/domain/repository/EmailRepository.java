package org.debugroom.sample.cassandra.pattern1.domain.repository;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

import org.debugroom.sample.cassandra.pattern1.domain.entity.Email;

public interface EmailRepository extends CrudRepository<Email, String>{
	
    // Use secondary index or materialized view depending on data cardinality.
    // (Not recommend) 1. Create secondary index
    // "create index on sample.email (user_id);"
	   // @Query("select * from email where user_id = ?0")
    // (Not recommend) 2. Add allow filtering option to cql.
	   @Query("select * from email where user_id = ?0 allow filtering")
    // (Recommend) 3. Use materialized view 
	   // "create materialized view email_by_user_id as select email, user_id, ver, last_updated_date from email where user_id is not null and email is not null primary key(user_id, email);"
    //@Query("select * from email_by_user_id where user_id = ?0")
    public List<Email> findByUserId(Long userId);;

}
