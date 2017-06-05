package org.debugroom.sample.docker.boot.domain.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the company database table.
 * 
 */
@Entity
@NamedQuery(name="Company.findAll", query="SELECT c FROM Company c")
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="company_id")
	private String companyId;

	@Column(name="company_name")
	private String companyName;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="company")
	private Set<User> usrs;

	public Company() {
	}

	public String getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Set<User> getUsrs() {
		return this.usrs;
	}

	public void setUsrs(Set<User> usrs) {
		this.usrs = usrs;
	}

	public User addUsr(User usr) {
		getUsrs().add(usr);
		usr.setCompany(this);

		return usr;
	}

	public User removeUsr(User usr) {
		getUsrs().remove(usr);
		usr.setCompany(null);

		return usr;
	}

}