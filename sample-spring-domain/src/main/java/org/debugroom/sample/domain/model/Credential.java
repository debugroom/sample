package org.debugroom.sample.domain.model;

import java.io.Serializable;

import java.util.Date;
import java.util.TimeZone;

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
@Table(name = "CREDENTIAL")
public class Credential implements Serializable{

	private static final long serialVersionUID = -1768774289814360236L;
	
	private CredentialPK credentialPK;
	private String credentialType;
	private String credentialKey;
    private Date expiredDateAndTime;
    private TimeZone timezone;
    private User user;
    
	@EmbeddedId
	public CredentialPK getCredentialPK() {
		return credentialPK;
	}
	public void setCredentialPK(CredentialPK credentialPK) {
		this.credentialPK = credentialPK;
	}
	@Column(name = "CREDENTIAL_TYPE")
	public String getCredentialType() {
		return credentialType;
	}
	public void setCredentialType(String credentialType) {
		this.credentialType = credentialType;
	}
	@Column(name = "CREDENTIAL_KEY")
	public String getCredentialKey() {
		return credentialKey;
	}
	public void setCredentialKey(String credentialKey) {
		this.credentialKey = credentialKey;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "EXPIRED_DATE_AND_TIME")
	public Date getExpiredDateAndTime() {
		return expiredDateAndTime;
	}
	public void setExpiredDateAndTime(Date expiredDateAndTime) {
		this.expiredDateAndTime = expiredDateAndTime;
	}
	@Transient
	public TimeZone getTimezone() {
		return timezone;
	}
	public void setTimezone(TimeZone timezone) {
		this.timezone = timezone;
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
	public String toString(){
		return new ToStringBuilder(this)
					.append("credentialPK", getCredentialPK())
					.append("credentialType", getCredentialType())
					.append("credentialKey", getCredentialKey())
					.append("expiredDateAndTime", getExpiredDateAndTime())
					.toString();
	}
	
	@Override
	public boolean equals(Object other){
		if((this == other)){
			return true;
		}
		if(!(other instanceof Credential)){
			return false;
		}
		Credential credential = (Credential)other;
		return new EqualsBuilder()
					.append(this.getCredentialPK(), credential.getCredentialPK())
					.isEquals();
	}
	
	@Override
	public int hashCode(){
		return new HashCodeBuilder()
					.append(getCredentialPK())
					.toHashCode();
	}
	
}
