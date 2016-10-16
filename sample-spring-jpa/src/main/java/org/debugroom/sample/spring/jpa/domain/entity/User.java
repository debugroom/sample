package org.debugroom.sample.spring.jpa.domain.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.NamedQuery;

import lombok.Builder;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Builder
@Entity
@Table(name = "usr", schema = "public", 
		uniqueConstraints = @UniqueConstraint(columnNames = "login_id") )
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {

	private static final long serialVersionUID = -3097005474180552877L;

	@Id
	@Column(name = "user_id", unique = true, nullable = false, length = 8)
	private String userId;

    @Column(name = "last_updated_date")
	private Timestamp lastUpdatedDate;

    @Column(name = "login_id", unique = true, length = 64)
	private String loginId;

    @Column(name = "user_name", length = 50)
	private String userName;

    @Column(name = "ver")
	private Integer ver;

	@OneToOne(mappedBy="usr", optional=false, fetch= FetchType.LAZY,
			cascade= CascadeType.ALL, orphanRemoval = true)
	private Address address;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usr", cascade= CascadeType.ALL, orphanRemoval=true)
	private Set<Affiliation> affiliations;

	@OneToMany(mappedBy="usr", cascade= CascadeType.ALL, orphanRemoval = true)
	private Set<Email> emails;

	public User() {
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Timestamp getLastUpdatedDate() {
		return this.lastUpdatedDate;
	}

	public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public String getLoginId() {
		return this.loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getVer() {
		return this.ver;
	}

	public void setVer(Integer ver) {
		this.ver = ver;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<Affiliation> getAffiliations() {
		return this.affiliations;
	}

	public void setAffiliations(Set<Affiliation> affiliations) {
		this.affiliations = affiliations;
	}

	public Affiliation addAffiliation(Affiliation affiliation) {
		getAffiliations().add(affiliation);
		affiliation.setUsr(this);

		return affiliation;
	}

	public Affiliation removeAffiliation(Affiliation affiliation) {
		getAffiliations().remove(affiliation);
		affiliation.setUsr(null);

		return affiliation;
	}

	public Set<Email> getEmails() {
		return this.emails;
	}

	public void setEmails(Set<Email> emails) {
		this.emails = emails;
	}

	public Email addEmail(Email email) {
		getEmails().add(email);
		email.setUsr(this);

		return email;
	}

	public Email removeEmail(Email email) {
		getEmails().remove(email);
		email.setUsr(null);

		return email;
	}

}