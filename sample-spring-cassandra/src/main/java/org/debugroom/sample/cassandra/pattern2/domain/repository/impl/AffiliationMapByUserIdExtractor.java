package org.debugroom.sample.cassandra.pattern2.domain.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cassandra.core.ResultSetExtractor;
import org.springframework.dao.DataAccessException;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.exceptions.DriverException;

public class AffiliationMapByUserIdExtractor 
    implements ResultSetExtractor<Map<Long, List<Long>>>{

	@Override
	public Map<Long, List<Long>> extractData(ResultSet resultSet) 
			throws DriverException, DataAccessException {
		
		Map<Long, List<Long>> groupIdsMap = new HashMap<Long, List<Long>>();
		
		while(resultSet.iterator().hasNext()){
			Row row = resultSet.one();
			Long userId = row.getLong("user_id");
			List<Long> groupIds = groupIdsMap.get(userId);
			if(null == groupIds){
				groupIds = new ArrayList<Long>();
				groupIdsMap.put(userId, groupIds);
			}
			groupIds.add(row.getLong("group_id"));
		}
		return groupIdsMap;

	}

}
