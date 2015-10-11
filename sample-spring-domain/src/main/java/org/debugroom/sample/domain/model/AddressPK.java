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
public class AddressPK implements Serializable{

	private static final long serialVersionUID = 5431456096388941136L;
	
	private String userId;
	private int addressNo;
	
	public AddressPK(){}
	public AddressPK(String userId){
		this.userId = userId;
	}
	public AddressPK(String userId, int addressNo){
		this.userId = userId;
		this.addressNo = addressNo;
	}
	
	@Column(name = "USER_ID")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name = "ADDRESS_NO")
	public int getAddressNo() {
		return addressNo;
	}
	public void setAddressNo(int addressNo) {
		this.addressNo = addressNo;
	}
	
	@Override
	public String toString(){
		return new ToStringBuilder(this)
					.append("userId : ", getUserId())
					.append("addressNo : ", getAddressNo())
					.toString();
	}
	
	@Override
	public boolean equals(Object other){
		if((this == other)){
			return true;
		}
		if(!(other instanceof AddressPK)){
			return false;
		}
		AddressPK addressPK = (AddressPK)other;
		return new EqualsBuilder()
					.append(this.getUserId(), addressPK.getUserId())
					.append(this.getAddressNo(), addressPK.getAddressNo())
					.isEquals();
	}
	
	@Override
	public int hashCode(){
		return new HashCodeBuilder()
					.append(getUserId())
					.append(getAddressNo())
					.toHashCode();
	}

}
