package org.debugroom.sample.javaee6.domain.model.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Builder;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Builder
/**
 * The primary key class for the email database table.
 * 
 */
@Embeddable
public class EmailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="company_id", insertable=false, updatable=false)
	private String companyId;

	@Column(name="user_id", insertable=false, updatable=false)
	private String userId;

	@Column(name="email_no")
	private Integer emailNo;

	public EmailPK() {
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
	public Integer getEmailNo() {
		return this.emailNo;
	}
	public void setEmailNo(Integer emailNo) {
		this.emailNo = emailNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EmailPK)) {
			return false;
		}
		EmailPK castOther = (EmailPK)other;
		return 
			this.companyId.equals(castOther.companyId)
			&& this.userId.equals(castOther.userId)
			&& this.emailNo.equals(castOther.emailNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.companyId.hashCode();
		hash = hash * prime + this.userId.hashCode();
		hash = hash * prime + this.emailNo.hashCode();
		
		return hash;
	}
}