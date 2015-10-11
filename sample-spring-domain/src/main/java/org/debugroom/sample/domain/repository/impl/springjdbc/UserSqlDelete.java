package org.debugroom.sample.domain.repository.impl.springjdbc;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

/**
 * 
 * @author org.debugroom
 *
 */
public class UserSqlDelete extends SqlUpdate{

	private static final String QUERY = "DELETE FROM DUSER" +
										" WHERE USER_ID = ?";
	
	public UserSqlDelete(DataSource dataSource){
		super(dataSource, QUERY);
		super.declareParameter(new SqlParameter("USER_ID", Types.VARCHAR));
		super.compile();
	}
	
}
