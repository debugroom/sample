package org.debugroom.sample.docker.boot.domain.entity;

import java.io.Serializable;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the phone database table.
 * 
 */
@Entity
@NamedQuery(name="Phone.findAll", query="SELECT p FROM Phone p")
public class Phone implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PhonePK id;

	@Column(name="phone_number")
	private String phoneNumber;

	@Column(name="related_address_no")
	private Integer relatedAddressNo;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="company_id", referencedColumnName="company_id", insertable=false, updatable=false),
		@JoinColumn(name="user_id", referencedColumnName="user_id", insertable=false, updatable=false)
		})
	@JsonIgnore
	private User usr;

	public Phone() {
	}

	public PhonePK getId() {
		return this.id;
	}

	public void setId(PhonePK id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getRelatedAddressNo() {
		return this.relatedAddressNo;
	}

	public void setRelatedAddressNo(Integer relatedAddressNo) {
		this.relatedAddressNo = relatedAddressNo;
	}

	public User getUsr() {
		return this.usr;
	}

	public void setUsr(User usr) {
		this.usr = usr;
	}

}