package org.debugroom.sample.cassandra.pattern1.domain.repository.impl;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.cassandra.core.ResultSetExtractor;
import org.springframework.dao.DataAccessException;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.UDTValue;
import com.datastax.driver.core.exceptions.DriverException;

import org.debugroom.sample.cassandra.pattern1.domain.entity.GroupOfUser;
import org.debugroom.sample.cassandra.pattern1.domain.entity.User;

public class UserMapExtractor implements ResultSetExtractor<Map<Long, User>> {

	@Override
	public Map<Long, User> extractData(ResultSet resultSet) 
			throws DriverException, DataAccessException {
		Map<Long, User> userMap = new HashMap<Long, User>();
		
		while(resultSet.iterator().hasNext()){
			Row row = resultSet.one();
			List<GroupOfUser> groupOfUsers = new ArrayList<GroupOfUser>();
			User user = User.builder()
							.userId(row.getLong("user_id"))
							.userName(row.getString("user_name"))
							.isEnabled(row.getBool("is_enabled"))
							.isLocked(row.getBool("is_locked"))
							.isAdmin(row.getBool("is_admin"))
							.address(row.getUDTValue("address"))
//							.groups(row.getList("groups", GroupOfUser.class))
							.groups(groupOfUsers)
							.ver(row.getInt("ver"))
							.lastUpdatedDate(row.getTimestamp("last_updated_date"))
							.build();
			@SuppressWarnings("unchecked")
			List<UDTValue> groups = (List<UDTValue>) row.getObject("groups");
			for(UDTValue udtValue : groups){
				GroupOfUser groupOfUser =  new GroupOfUser();
				groupOfUser.setGroupId(udtValue.getLong("group_id"));
				groupOfUser.setGroupName(udtValue.getString("group_name"));
				groupOfUser.setVer(udtValue.getInt("ver"));
				groupOfUser.setLastUpdatedDate(udtValue.getTimestamp("last_updated_date"));
				groupOfUsers.add(groupOfUser);
			}
			userMap.put(row.getLong("user_id"), user);
		}
		return userMap;
	}

}
