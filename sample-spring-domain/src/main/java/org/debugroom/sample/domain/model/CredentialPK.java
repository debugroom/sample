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
public class CredentialPK implements Serializable{

	private static final long serialVersionUID = 9200336439056430539L;
	
	private String userId;
	private String credentialId;
	
	@Column(name = "USER_ID")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name = "CREDENTIAL_ID")
	public String getCredentialId() {
		return credentialId;
	}
	public void setCredentialId(String credentialId) {
		this.credentialId = credentialId;
	}
	
	@Override
	public String toString(){
		return new ToStringBuilder(this)
					.append("userId", getUserId())
					.append("credentialId", getCredentialId())
					.toString();
	}
	
	@Override
	public boolean equals(Object other){
		if((this == other)){
			return true;
		}
		if(!(other instanceof CredentialPK)){
			return false;
		}
		CredentialPK credentialPK = (CredentialPK)other;
		return new EqualsBuilder()
					.append(this.getUserId(), credentialPK.getUserId())
					.append(this.getCredentialId(), credentialPK.getCredentialId())
					.isEquals();
	}
	
	@Override
	public int hashCode(){
		return new HashCodeBuilder()
					.append(getUserId())
					.append(getCredentialId())
					.toHashCode();
	}
	
}
