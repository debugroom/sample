package org.debugroom.sample.javaee6.domain.model.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;

import java.util.Set;

import lombok.Builder;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Builder

/**
 * The persistent class for the usr database table.
 * 
 */
@Entity
@Table(name="usr")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserPK id;

	@Column(name="authority_level")
	private Integer authorityLevel;

	private String birthday;

	@Column(name="is_login")
	private Boolean isLogin;

	@Column(name="login_id")
	private String loginId;

	@Column(name="user_name")
	private String userName;

	//bi-directional many-to-one association to Address
	@OneToMany(mappedBy="usr")
	@JsonManagedReference
	private Set<Address> addresses;

	//bi-directional many-to-one association to Credential
	@OneToMany(mappedBy="usr")
	@JsonManagedReference
	private Set<Credential> credentials;

	//bi-directional many-to-one association to Email
	@OneToMany(mappedBy="usr")
	@JsonManagedReference
	private Set<Email> emails;

	//bi-directional many-to-one association to Phone
	@OneToMany(mappedBy="usr")
	@JsonManagedReference
	private Set<Phone> phones;

	//bi-directional many-to-one association to Company
	@ManyToOne
	@JoinColumn(name="company_id", insertable=false, updatable=false)
	@JsonBackReference
	private Company company;

	public User() {
	}

	public UserPK getId() {
		return this.id;
	}

	public void setId(UserPK id) {
		this.id = id;
	}

	public Integer getAuthorityLevel() {
		return this.authorityLevel;
	}

	public void setAuthorityLevel(Integer authorityLevel) {
		this.authorityLevel = authorityLevel;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Boolean getIsLogin() {
		return this.isLogin;
	}

	public void setIsLogin(Boolean isLogin) {
		this.isLogin = isLogin;
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

	public Set<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	public Address addAddress(Address address) {
		getAddresses().add(address);
		address.setUsr(this);

		return address;
	}

	public Address removeAddress(Address address) {
		getAddresses().remove(address);
		address.setUsr(null);

		return address;
	}

	public Set<Credential> getCredentials() {
		return this.credentials;
	}

	public void setCredentials(Set<Credential> credentials) {
		this.credentials = credentials;
	}

	public Credential addCredential(Credential credential) {
		getCredentials().add(credential);
		credential.setUsr(this);

		return credential;
	}

	public Credential removeCredential(Credential credential) {
		getCredentials().remove(credential);
		credential.setUsr(null);

		return credential;
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

	public Set<Phone> getPhones() {
		return this.phones;
	}

	public void setPhones(Set<Phone> phones) {
		this.phones = phones;
	}

	public Phone addPhone(Phone phone) {
		getPhones().add(phone);
		phone.setUsr(this);

		return phone;
	}

	public Phone removePhone(Phone phone) {
		getPhones().remove(phone);
		phone.setUsr(null);

		return phone;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}