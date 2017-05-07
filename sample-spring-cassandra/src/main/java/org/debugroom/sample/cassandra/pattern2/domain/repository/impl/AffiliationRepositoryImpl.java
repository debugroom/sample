package org.debugroom.sample.cassandra.pattern2.domain.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.repository.NoRepositoryBean;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;

import org.debugroom.sample.cassandra.pattern2.domain.repository.AffiliationRepositoryCustom;

@NoRepositoryBean
public class AffiliationRepositoryImpl implements AffiliationRepositoryCustom{

	@Autowired
	@Qualifier("cassandraTemplate")
	CassandraOperations cassandraOperations;

	@Override
	public Map<Long, List<Long>> findGroupIdsMapByUserId() {
		Select select = QueryBuilder.select().from("affiliation");
		return cassandraOperations.query(select, new AffiliationMapByUserIdExtractor());
	}

}
