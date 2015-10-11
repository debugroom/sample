package org.debugroom.sample.app.common;

import java.io.Serializable;

import java.util.Date;

/**
 * 
 * @author org.debugroom
 *
 */
public class SampleParameters implements Serializable{

	private static final long serialVersionUID = 5208230835863682466L;

	private String userId;
	private String userName;
	private Date lastUpdatedDate;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	
}
