package org.debugroom.sample.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author org.debugroom
 *
 */
@Entity
@Table(name = "ADDRESS")
public class Address implements Serializable{

	private static final long serialVersionUID = 6012607856772915311L;

	private AddressPK addressPK;
	private String address;
	private String addressDetails;
	private User user;

	@EmbeddedId
	public AddressPK getAddressPK() {
		return addressPK;
	}
	public void setAddressPK(AddressPK addressPK) {
		this.addressPK = addressPK;
	}
	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "ADDRESS_DETAILS")
	public String getAddressDetails() {
		return addressDetails;
	}
	public void setAddressDetails(String addressDetails) {
		this.addressDetails = addressDetails;
	}
	
	@ManyToOne
	@JoinColumn(name = "USER_ID", insertable=false, updatable=false)
	@JsonIgnore
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString(){
		return new ToStringBuilder(this)
					.append("addressPK : ", getAddressPK())
					.append("address : ", getAddress())
					.append("addressDetails : ", getAddressDetails())
					.toString();
	}
	
	@Override
	public boolean equals(Object other){
		if((this == other)){
			return true;
		}
		if(!(other instanceof Address)){
			return false;
		}
		Address address = (Address)other;
		return new EqualsBuilder()
					.append(this.getAddressPK(), address.getAddressPK())
					.isEquals();
	}
	
	@Override
	public int hashCode(){
		return new HashCodeBuilder()
					.append(getAddressPK())
					.toHashCode();
	}

}
