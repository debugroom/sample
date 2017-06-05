package org.debugroom.sample.docker.boot.domain.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the credential database table.
 * 
 */
@Entity
@NamedQuery(name="Credential.findAll", query="SELECT c FROM Credential c")
public class Credential implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CredentialPK id;

	@Column(name="access_token")
	private String accessToken;

	@Column(name="credential_key")
	private String credentialKey;

	@Column(name="credential_type")
	private String credentialType;

	@Column(name="key_expired_date")
	private Timestamp keyExpiredDate;

	@Column(name="token_expired_date")
	private Timestamp tokenExpiredDate;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="company_id", referencedColumnName="company_id", insertable=false, updatable=false),
		@JoinColumn(name="user_id", referencedColumnName="user_id", insertable=false, updatable=false)
		})
	@JsonIgnore
	private User usr;

	public Credential() {
	}

	public CredentialPK getId() {
		return this.id;
	}

	public void setId(CredentialPK id) {
		this.id = id;
	}

	public String getAccessToken() {
		return this.accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getCredentialKey() {
		return this.credentialKey;
	}

	public void setCredentialKey(String credentialKey) {
		this.credentialKey = credentialKey;
	}

	public String getCredentialType() {
		return this.credentialType;
	}

	public void setCredentialType(String credentialType) {
		this.credentialType = credentialType;
	}

	public Timestamp getKeyExpiredDate() {
		return this.keyExpiredDate;
	}

	public void setKeyExpiredDate(Timestamp keyExpiredDate) {
		this.keyExpiredDate = keyExpiredDate;
	}

	public Timestamp getTokenExpiredDate() {
		return this.tokenExpiredDate;
	}

	public void setTokenExpiredDate(Timestamp tokenExpiredDate) {
		this.tokenExpiredDate = tokenExpiredDate;
	}

	public User getUsr() {
		return this.usr;
	}

	public void setUsr(User usr) {
		this.usr = usr;
	}

}