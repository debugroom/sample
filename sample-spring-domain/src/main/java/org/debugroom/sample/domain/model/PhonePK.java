package org.debugroom.sample.domain.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Column;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Multiple primary key class.
 * @author org.debugroom
 *
 */
@Embeddable
public class PhonePK implements Serializable{

	private static final long serialVersionUID = -7680673078626743421L;

	private String userId;
	private int phoneNo;
	
	public PhonePK(){}
	public PhonePK(String userId){
		this.userId = userId;
	}
	public PhonePK(String userId, int phoneNo){
		this.userId = userId;
		this.phoneNo = phoneNo;
	}
	@Column(name = "USER_ID")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "PHONE_NO")
	public int getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(int phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
					.append(getUserId())
					.append(getPhoneNo())
					.toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if(this == other){
			return true;
		}
		if(!(other instanceof PhonePK)){
			return false;
		}
		PhonePK phonePK = (PhonePK)other;
		return new EqualsBuilder()
					.append(this.getUserId(), phonePK.getUserId())
					.append(this.getPhoneNo(), phonePK.getPhoneNo())
					.isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
					.append("userId : ", getUserId())
					.append("phoneNo : ", getPhoneNo())
					.toString();
	}
	
}
