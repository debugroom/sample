package org.debugroom.sample.docker.boot.domain.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the phone database table.
 * 
 */
@Embeddable
public class PhonePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="company_id", insertable=false, updatable=false)
	private String companyId;

	@Column(name="user_id", insertable=false, updatable=false)
	private String userId;

	@Column(name="phone_no")
	private Integer phoneNo;

	public PhonePK() {
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
	public Integer getPhoneNo() {
		return this.phoneNo;
	}
	public void setPhoneNo(Integer phoneNo) {
		this.phoneNo = phoneNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PhonePK)) {
			return false;
		}
		PhonePK castOther = (PhonePK)other;
		return 
			this.companyId.equals(castOther.companyId)
			&& this.userId.equals(castOther.userId)
			&& this.phoneNo.equals(castOther.phoneNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.companyId.hashCode();
		hash = hash * prime + this.userId.hashCode();
		hash = hash * prime + this.phoneNo.hashCode();
		
		return hash;
	}
}