package org.debugroom.sample.javaee6.domain.model.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Builder;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Builder
/**
 * The primary key class for the address database table.
 * 
 */
@Embeddable
public class AddressPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="company_id", insertable=false, updatable=false)
	private String companyId;

	@Column(name="user_id", insertable=false, updatable=false)
	private String userId;

	@Column(name="address_no")
	private Integer addressNo;

	public AddressPK() {
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
	public Integer getAddressNo() {
		return this.addressNo;
	}
	public void setAddressNo(Integer addressNo) {
		this.addressNo = addressNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AddressPK)) {
			return false;
		}
		AddressPK castOther = (AddressPK)other;
		return 
			this.companyId.equals(castOther.companyId)
			&& this.userId.equals(castOther.userId)
			&& this.addressNo.equals(castOther.addressNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.companyId.hashCode();
		hash = hash * prime + this.userId.hashCode();
		hash = hash * prime + this.addressNo.hashCode();
		
		return hash;
	}
}