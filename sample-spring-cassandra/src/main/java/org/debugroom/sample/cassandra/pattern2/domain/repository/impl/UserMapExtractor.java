package org.debugroom.sample.cassandra.pattern2.domain.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.debugroom.sample.cassandra.pattern2.domain.entity.Address;
import org.debugroom.sample.cassandra.pattern2.domain.entity.Credential;
import org.debugroom.sample.cassandra.pattern2.domain.entity.Email;
import org.debugroom.sample.cassandra.pattern2.domain.entity.User;
import org.springframework.cassandra.core.ResultSetExtractor;
import org.springframework.dao.DataAccessException;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.UDTValue;
import com.datastax.driver.core.exceptions.DriverException;

public class UserMapExtractor implements ResultSetExtractor<Map<Long, User>>{

	@SuppressWarnings("unchecked")
	@Override
	public Map<Long, User> extractData(ResultSet resultSet) 
			throws DriverException, DataAccessException {
		
		Map<Long, User> userMap = new HashMap<Long, User>();
		
		while(resultSet.iterator().hasNext()){
			Row row = resultSet.one();
			UDTValue address = row.getUDTValue("address");
			List<Email> emails = new ArrayList<Email>();
			List<Credential> credentials = new ArrayList<Credential>();
			Long userId = row.getLong("user_id");
			User user = User.builder()
					        .userId(userId)
					        .userName(row.getString("user_name"))
					        .isEnabled(row.getBool("is_enabled"))
					        .isLocked(row.getBool("is_locked"))
					        .isAdmin(row.getBool("is_admin"))
					        .emails(emails)
					        .credentials(credentials)
					        .build();
			if(address != null){
				user.setAddress(Address.builder()
						.zipCd(address.getString("zip_cd"))
						.address(address.getString("address"))
						.ver(address.getInt("ver"))
						.lastUpdatedDate(row.getTimestamp("last_updated_date"))
						.build());
			}
			
			for(UDTValue udtValue : (List<UDTValue>)row.getObject("emails")){
				emails.add(Email.builder()
						.email(udtValue.getString("email"))
						.ver(udtValue.getInt("ver"))
						.lastUpdatedDate(udtValue.getTimestamp("last_updated_date"))
						.build());
			}

			for(UDTValue udtValue : (List<UDTValue>)row.getObject("credentials")){
				credentials.add(Credential.builder()
						.credentialType(udtValue.getString("credential_type"))
						.credentialKey(udtValue.getString("credential_key"))
						.expiredDate(udtValue.getTimestamp("expired_date"))
						.ver(udtValue.getInt("ver"))
						.lastUpdatedDate(udtValue.getTimestamp("last_updated_date"))
						.build());
			}
			userMap.put(userId, user);
		}
		return userMap;
	}

}
