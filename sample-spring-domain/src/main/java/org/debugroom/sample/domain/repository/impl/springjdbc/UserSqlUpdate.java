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
public class UserSqlUpdate extends SqlUpdate{

	private static final String QUERY = "UPDATE DUSER SET" +
										" USER_NAME = ?" +
										", LAST_UPDATED_DATE_AND_TIME = ?" +
										", IS_LOGIN = ?" +
										" WHERE USER_ID = ?";
	
	public UserSqlUpdate(DataSource dataSource){
		super(dataSource, QUERY);
		super.declareParameter(new SqlParameter("USER_NAME", Types.VARCHAR));
		super.declareParameter(new SqlParameter("LAST_UPDATED_DATE_AND_TIME", Types.TIMESTAMP));
		super.declareParameter(new SqlParameter("IS_LOGIN", Types.BOOLEAN));
		super.declareParameter(new SqlParameter("USER_ID", Types.VARCHAR));
		super.compile();
	}
	
}
