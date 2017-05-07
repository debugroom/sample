package org.debugroom.sample.cassandra.pattern2.domain.repository.impl;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

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

public class UserMapByEmailExtractor 
                               implements ResultSetExtractor<Map<String, User>>{

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, User> extractData(ResultSet resultSet) 
			throws DriverException, DataAccessException {
		
		Map<String, User> mappedUser = new HashMap<String, User>();
		
		while(resultSet.iterator().hasNext()){
			Row row = resultSet.one();
			UDTValue address = row.getUDTValue("address");
			List<Email> emails = new ArrayList<Email>();
			List<Credential> credentials = new ArrayList<Credential>();
			User user = User.builder()
					        .userId(row.getLong("user_id"))
					        .userName(row.getString("user_name"))
					        .isEnabled(row.getBool("is_enabled"))
					        .isLocked(row.getBool("is_locked"))
					        .isAdmin(row.getBool("is_admin"))
					        .emails(emails)
					        .credentials(credentials)
					        .ver(row.getInt("ver"))
					        .lastUpdatedDate(row.getTimestamp("last_updated_date"))
					        .build();
			if(address != null){
				user.setAddress(Address.builder()
						.zipCd(address.getString("zip_cd"))
						.address(address.getString("address"))
						.ver(address.getInt("ver"))
						.lastUpdatedDate(address.getTimestamp("last_updated_date"))
						.build());
			}

			for(UDTValue udtValue : (List<UDTValue>)row.getObject("emails")){
				String email = udtValue.getString("email");
				emails.add(Email.builder()
				           .email(email)
				           .ver(udtValue.getInt("ver"))
				           .lastUpdatedDate(udtValue.getTimestamp("last_updated_date"))
				           .build());
				mappedUser.put(email, user);
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
		}
		return mappedUser;
	}

}
