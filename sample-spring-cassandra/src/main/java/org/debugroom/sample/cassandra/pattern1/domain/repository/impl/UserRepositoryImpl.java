package org.debugroom.sample.cassandra.pattern1.domain.repository.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.cassandra.core.CassandraAdminOperations;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.repository.NoRepositoryBean;

import com.datastax.driver.core.KeyspaceMetadata;
import com.datastax.driver.core.UDTValue;
import com.datastax.driver.core.UserType;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;

import org.debugroom.sample.cassandra.pattern1.domain.entity.User;
import org.debugroom.sample.cassandra.pattern1.domain.repository.UserRepositoryCustom;

@NoRepositoryBean
public class UserRepositoryImpl implements UserRepositoryCustom{

	@Autowired
	@Qualifier("cassandraTemplate")
	CassandraOperations cassandraOperations;
	
	@Autowired
	@Qualifier("cassandraAdminOperations")
	CassandraAdminOperations cassandraAdminOperations;
	
	@Override
	public Map<Long, User> findAllForMap() {
		Select select = QueryBuilder.select().from("users");
		return cassandraOperations.query(select, new UserMapExtractor());
	}

	@Override
	public User addAddress(User user, String zipCd, String address) {
		// For instantiating udtvalue object, need to use cassandraadminoperations.
		KeyspaceMetadata keyspaceMetadata = cassandraAdminOperations.getKeyspaceMetadata();
		UserType userTypeAddress = keyspaceMetadata.getUserType("addressofuser");

		UDTValue udtValue = userTypeAddress.newValue();
		udtValue.setString("zip_cd", zipCd);
		udtValue.setString("address", address);
		udtValue.setInt("ver", 0);
		udtValue.setTimestamp("last_updated_date", new Date());
		user.setAddress(udtValue);
		
		cassandraOperations.insert(user);

		return user;

	}

}
