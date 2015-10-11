package org.debugroom.sample.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

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
@Table(name = "PHONE")
public class Phone implements Serializable{

	private PhonePK phonePK;
	private String phoneNumber;
	private User user;
	
	@EmbeddedId
	public PhonePK getPhonePK() {
		return phonePK;
	}
	public void setPhonePK(PhonePK phonePK) {
		this.phonePK = phonePK;
	}
	@Column(name = "PHONE_NUMBER")
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@ManyToOne
	@JoinColumn(name = "USER_ID",  insertable=false, updatable=false)
	@JsonIgnore
	public User getUser() {
		return user;
	}
	@JsonIgnore
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
					.append(getPhonePK())
					.toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if(this == other){
			return true;
		}
		if(!(other instanceof Phone)){
			return false;
		}
		Phone phone = (Phone)other;
		return new EqualsBuilder()
					.append(this.getPhonePK(), phone.getPhonePK())
					.isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
					.append("phonePK : ", getPhonePK())
					.append("phoneNumber : ", getPhoneNumber())
					.toString();
	}
	
}
