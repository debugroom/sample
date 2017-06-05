package org.debugroom.sample.docker.boot.domain.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the credential database table.
 * 
 */
@Embeddable
public class CredentialPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="company_id", insertable=false, updatable=false)
	private String companyId;

	@Column(name="user_id", insertable=false, updatable=false)
	private String userId;

	@Column(name="credential_no")
	private Integer credentialNo;

	public CredentialPK() {
	}
	public String getCompanyId() {
		return this.companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getUserId() {
		return this.userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getCredentialNo() {
		return this.credentialNo;
	}
	public void setCredentialNo(Integer credentialNo) {
		this.credentialNo = credentialNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CredentialPK)) {
			return false;
		}
		CredentialPK castOther = (CredentialPK)other;
		return 
			this.companyId.equals(castOther.companyId)
			&& this.userId.equals(castOther.userId)
			&& this.credentialNo.equals(castOther.credentialNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.companyId.hashCode();
		hash = hash * prime + this.userId.hashCode();
		hash = hash * prime + this.credentialNo.hashCode();
		
		return hash;
	}
}