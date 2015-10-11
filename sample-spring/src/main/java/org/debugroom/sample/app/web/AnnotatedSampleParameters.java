package org.debugroom.sample.app.web;

import java.io.Serializable;

import java.util.Date;

//import javax.validation.constraints.AssertFalse;
//import javax.validation.constraints.AssertTrue;
//import javax.validation.constraints.DecimalMax;
//import javax.validation.constraints.DecimalMin;
//import javax.validation.constraints.Max;
//import javax.validation.constraints.Min;
//import javax.validation.constraints.Digits;
//import javax.validation.constraints.Future;
import javax.validation.constraints.Past;
//import javax.validation.constraints.Null;
import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 
 * @author org.debugroom
 *
 */
public class AnnotatedSampleParameters implements Serializable{

	private static final long serialVersionUID = 9146960192270596936L;
	
	@NotNull
	@Size(min=7, max=7)
	private String userId;
	@Size(min=0, max=50)
	private String userName;
	@Past
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
