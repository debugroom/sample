package org.debugroom.sample.domain.repository.impl.springjdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import org.debugroom.sample.domain.model.User;

/**
 * 
 * @author org.debugroom
 *
 */
public class UserMappingSqlQuery extends MappingSqlQuery<User>{

	private static final String QUERY = "SELECT" +
											" USER_ID" +
											", USER_NAME" +
											", LAST_UPDATE_DATE_AND_TIME" +
											", IS_LOGIN" +
											" FROM DUSER WHERE USER_ID = ?";
	
	public UserMappingSqlQuery(DataSource dataSource){
		super(dataSource, QUERY);
		super.declareParameter(new SqlParameter("USER_ID", Types.VARCHAR));
		compile();
	}
	
	@Override
	protected User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		User user = new User();
		user.setUserId(resultSet.getString("USER_ID"));
		user.setUserName(resultSet.getString("USER_NAME"));
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(resultSet.getTimestamp("LAST_UPDATED_DATE_AND_TIME"));
		user.setLastUpdatedDateAndTime(calendar.getTime());
		user.setTimeZone(calendar.getTimeZone());
		user.setLogin(resultSet.getBoolean("IS_LOGIN"));
		return user;
	}

}
