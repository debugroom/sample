package org.debugroom.sample.cassandra.pattern2.domain.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.cassandra.core.CassandraAdminOperations;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.repository.NoRepositoryBean;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;

import org.debugroom.sample.cassandra.pattern2.domain.entity.User;
import org.debugroom.sample.cassandra.pattern2.domain.repository.UserRepositoryCustom;

@NoRepositoryBean
public class UserRepositoryImpl implements UserRepositoryCustom{

	@Autowired
	@Qualifier("cassandraTemplate")
	CassandraOperations cassandraOperations;
	
	@Autowired
	@Qualifier("cassandraAdminOperations")
	CassandraAdminOperations cassandraAdminOperations;

	@Override
	public Map<String, List<User>> findAllForMappedListByZipCd() {
		Select select = QueryBuilder.select().from("users");
		return cassandraOperations.query(select, new UserMappedListByZipCdExtractor());
	}

	@Override
	public Map<String, User> findAllForMappByEmail() {
		Select select = QueryBuilder.select().from("users");
		return cassandraOperations.query(select, new UserMapByEmailExtractor());
	}

	@Override
	public Map<Long, User> findAllForMap() {
		Select select = QueryBuilder.select().from("users");
		return cassandraOperations.query(select, new UserMapExtractor());
	}

}
