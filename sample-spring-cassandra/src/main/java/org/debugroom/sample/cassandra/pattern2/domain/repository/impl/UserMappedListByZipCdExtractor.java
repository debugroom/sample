package org.debugroom.sample.cassandra.pattern2.domain.repository.impl;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import org.debugroom.sample.cassandra.pattern2.domain.entity.Address;
import org.debugroom.sample.cassandra.pattern2.domain.entity.Credential;
import org.debugroom.sample.cassandra.pattern2.domain.entity.Email;
import org.debugroom.sample.cassandra.pattern2.domain.entity.Group;
import org.debugroom.sample.cassandra.pattern2.domain.entity.User;
import org.springframework.cassandra.core.ResultSetExtractor;
import org.springframework.dao.DataAccessException;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.UDTValue;
import com.datastax.driver.core.exceptions.DriverException;

public class UserMappedListByZipCdExtractor implements 
                                    ResultSetExtractor<Map<String, List<User>>>{

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, List<User>> extractData(ResultSet resultSet) 
			throws DriverException, DataAccessException {
		Map<String, List<User>> mappedLists = new HashMap<String, List<User>>();
		
		while(resultSet.iterator().hasNext()){
			Row row = resultSet.one();
			UDTValue address = row.getUDTValue("address");
			List<Email> emails = new ArrayList<Email>();
			List<Credential> credentials = new ArrayList<Credential>();
			List<Group> groups = new ArrayList<Group>();
			User user = User.builder()
					        .userId(row.getLong("user_id"))
					        .userName(row.getString("user_name"))
					        .isEnabled(row.getBool("is_enabled"))
					        .isLocked(row.getBool("is_locked"))
					        .isAdmin(row.getBool("is_admin"))
					        .emails(emails)
					        .credentials(credentials)
					        .groups(groups)
					        .ver(row.getInt("ver"))
					        .lastUpdatedDate(row.getTimestamp("last_updated_date"))
					        .build();

			String zipCd = null;

			if(address != null){
				zipCd = address.getString("zip_cd");
				user.setAddress((Address.builder()
        		        .zipCd(zipCd)
        		        .address(address.getString("address"))
        		        .ver(address.getInt("ver"))
        		        .lastUpdatedDate(address.getTimestamp("last_updated_date"))
        		        .build()));
			}else{
				zipCd = "No address data.";
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

			List<User> users = mappedLists.get(zipCd);
			if(users == null){
				users = new ArrayList<User>();
				mappedLists.put(zipCd, users);
			}
			users.add(user);
			
		}
		return mappedLists;
	}

}
