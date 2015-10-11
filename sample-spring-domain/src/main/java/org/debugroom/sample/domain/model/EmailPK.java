package org.debugroom.sample.domain.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Column;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 
 * Multiple primary key class.
 * @author org.debugroom
 *
 */
@Embeddable
public class EmailPK implements Serializable{

	private static final long serialVersionUID = -4549961294279192115L;

	private String userId;
	private int emailNo;

	public EmailPK(){}
	public EmailPK(String userId){
		this.userId = userId;
	}
	public EmailPK(String userId, int emailNo){
		this.userId = userId;
		this.emailNo = emailNo;
	}
	@Column(name = "USER_ID")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name = "EMAIL_NO")
	public int getEmailNo() {
		return emailNo;
	}
	public void setEmailNo(int emailNo) {
		this.emailNo = emailNo;
	}
	
	@Override
	public String toString(){
		return new ToStringBuilder(this)
					.append("userId : ", getUserId())
					.append("emailNo : ", getEmailNo())
					.toString();
	}
	
	@Override
	public boolean equals(Object other){
		if((this == other)){
			return true;
		}
		if(!(other instanceof EmailPK)){
			return false;
		}
		EmailPK emailPK = (EmailPK)other;
		return new EqualsBuilder()
					.append(this.getUserId(), emailPK.getUserId())
					.append(this.getEmailNo(), emailPK.getEmailNo())
					.isEquals();
	} 
	
	@Override
	public int hashCode(){
		return new HashCodeBuilder()
					.append(getUserId())
					.append(getEmailNo())
					.toHashCode();
	}

}
