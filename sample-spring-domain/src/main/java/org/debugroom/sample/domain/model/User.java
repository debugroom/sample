package org.debugroom.sample.domain.model;

import java.io.Serializable;

import java.util.Set;
import java.util.Date;
import java.util.TimeZone;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * 
 * @author org.debugroom
 *
 */
@Entity
@Table(name = "DUSER")
public class User implements Serializable{

	private static final long serialVersionUID = 4502671017481402631L;
	
	private String userId;
	private String userName;
	private Set<Credential> credentials;
	private Date lastUpdatedDateAndTime;
	private TimeZone timeZone;
	private boolean isLogin;
	
	@Id
	@Column(name = "USER_ID")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name = "USER_NAME")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	public Set<Credential> getCredentials() {
		return credentials;
	}
	public void setCredentials(Set<Credential> credentials) {
		this.credentials = credentials;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_UPDATED_DATE_AND_TIME")
	public Date getLastUpdatedDateAndTime() {
		return lastUpdatedDateAndTime;
	}
	public void setLastUpdatedDateAndTime(Date lastUpdatedDateAndTime) {
		this.lastUpdatedDateAndTime = lastUpdatedDateAndTime;
	}
	@Transient
	public TimeZone getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}
	@Column(name = "IS_LOGIN")
	public boolean isLogin() {
		return isLogin;
	}
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
	
	@Override
	public String toString(){
		return new ToStringBuilder(this)
					.append("userId", getUserId())
					.append("userName", getUserName())
					.append("lastUpdatedDateAndTime", getLastUpdatedDateAndTime())
					.append("isLogin", isLogin())
					.toString();
	}
	
	@Override
	public boolean equals(Object other){
		if((this == other)){
			return true;
		}
		if(!(other instanceof User)){
			return false;
		}
		User user = (User)other;
		return new EqualsBuilder()
					.append(this.getUserId(), user.getUserId())
					.isEquals();
	}
	
	@Override
	public int hashCode(){
		return new HashCodeBuilder()
		.append(getUserId()).toHashCode();
	}
}
