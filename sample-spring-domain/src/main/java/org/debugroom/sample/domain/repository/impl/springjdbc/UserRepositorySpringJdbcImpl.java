package org.debugroom.sample.domain.repository.impl.springjdbc;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.object.SqlUpdate;

import org.debugroom.sample.domain.model.User;
import org.debugroom.sample.domain.repository.UserRepository;

/**
 * 
 * @author org.debugroom
 *
 */
public class UserRepositorySpringJdbcImpl extends 
					NamedParameterJdbcDaoSupport implements UserRepository{

	private SimpleJdbcInsert simpleJdbcInsert;
	private SqlUpdate userSqlDelete;
	private SqlUpdate userSqlUpdate;
	
	private static final String COUNT_QUERY = "SELECT COUNT(*) FROM DUSER";
	private static final String FIND_ONE_QUERY = "SELECT" +
												" USER_ID" +
												", USER_NAME" +
												", LAST_UPDATED_DATE_AND_TIME" +
												", IS_LOGIN" +
												" FROM DUSER WHERE" +
												" USER_ID = :userId";
	private static final String FIND_ALL_QUERY = "SELECT" +
												" USER_ID" +
												", USER_NAME" +
												", LAST_UPDATED_DATE_AND_TIME" +
												", IS_LOGIN" +
												" FROM DUSER";
	
	public void initDao() throws Exception{
		this.simpleJdbcInsert = new SimpleJdbcInsert(getDataSource())
										.withTableName("DUSER");
		this.userSqlDelete = new UserSqlDelete(getDataSource());
		this.userSqlUpdate = new UserSqlUpdate(getDataSource());
	}
	
	@Override
	public User findOne(String userId) {
		SqlParameterSource sqlParameterSource = 
				new MapSqlParameterSource("userId", userId);
		return getNamedParameterJdbcTemplate()
				.query(FIND_ONE_QUERY, sqlParameterSource, new UserResultSetExtractor());
	}

	@Override
	public List<User> findAll() {
		RowMapper<User> userRowMapper = new UserRowMapper();
		return getJdbcTemplate().query(FIND_ALL_QUERY, userRowMapper);
	}

	@Override
	public int getNumberOfUser() {
		return getJdbcTemplate().queryForObject(COUNT_QUERY, Integer.class);
	}

	@Override
	public void saveUser(User user) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("USER_NAME", user.getUserName());
		Calendar calendar = Calendar.getInstance(user.getTimeZone()); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		sdf.setTimeZone(user.getTimeZone());
		calendar.setTime(user.getLastUpdatedDateAndTime());
		parameterMap.put("LAST_UPDATED_DATE_AND_TIME", sdf.format(calendar.getTime()));
		parameterMap.put("IS_LOGIN", user.isLogin());
		parameterMap.put("USER_ID", user.getUserId());
		this.simpleJdbcInsert.execute(parameterMap);
	}

	@Override
	public void updateUser(User user) {
		Calendar calendar = Calendar.getInstance(user.getTimeZone()); 
		calendar.setTime(new Date());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		this.userSqlUpdate.update(user.getUserName(),
				 sdf.format(calendar.getTime()),
				 user.isLogin(),
				 user.getUserId());
	}

	@Override
	public void deleteUser(User user) {
		this.userSqlDelete.update(user.getUserId());
	}
	
	/**
	 * Callback interface for return type List.
	 * @author org.debugroom
	 *
	 */
	protected class UserRowMapper implements RowMapper<User>{
		@Override
		public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
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
	
	/**
	 * Callback interface for return type User.
	 * @author org.debugroom
	 *
	 */
	protected class UserResultSetExtractor implements ResultSetExtractor<User>{
		@Override
		public User extractData(ResultSet resultSet) throws SQLException,
				DataAccessException {
			if(resultSet.next()){
				User user = new User();
				user.setUserId(resultSet.getString("USER_ID"));
				user.setUserName(resultSet.getString("USER_NAME"));
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(resultSet.getTimestamp("LAST_UPDATED_DATE_AND_TIME"));
				user.setLastUpdatedDateAndTime(calendar.getTime());
				user.setTimeZone(calendar.getTimeZone());
				user.setLogin(resultSet.getBoolean("IS_LOGIN"));
				return user;
			}else{
				return null;
			}
		}
	}
	

}
