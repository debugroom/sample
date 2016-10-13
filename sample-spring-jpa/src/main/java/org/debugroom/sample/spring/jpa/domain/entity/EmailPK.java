package org.debugroom.sample.spring.jpa.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmailPK implements Serializable {

	private static final long serialVersionUID = -3546965750394227468L;

	@Column(name="user_id", insertable=false, updatable=false)
	private String userId;

	@Column(name="email_id")
	private String emailId;

	public EmailPK() {
	}
	public String getUserId() {
		return this.userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEmailId() {
		return this.emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
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
			this.userId.equals(castOther.userId)
			&& this.emailId.equals(castOther.emailId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId.hashCode();
		hash = hash * prime + this.emailId.hashCode();
		
		return hash;
	}
}