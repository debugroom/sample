package org.debugroom.sample.spring.jpa.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Builder;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Builder
@Embeddable
public class AffiliationPK implements Serializable {

	private static final long serialVersionUID = -1873418684097700980L;

	@Column(name="group_id", insertable=false, updatable=false, length=10)
	private String groupId;

	@Column(name="user_id", insertable=false, updatable=false, length=8)
	private String userId;

	public AffiliationPK() {
	}

	public String getGroupId() {
		return this.groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getUserId() {
		return this.userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AffiliationPK)) {
			return false;
		}
		AffiliationPK castOther = (AffiliationPK)other;
		return 
			this.groupId.equals(castOther.groupId)
			&& this.userId.equals(castOther.userId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.groupId.hashCode();
		hash = hash * prime + this.userId.hashCode();
		
		return hash;
	}
}