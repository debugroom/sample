package org.debugroom.sample.spring.jpa.domain.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.NamedQuery;

import lombok.Builder;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Builder
@Entity
@Table(name = "address", schema = "public")
@NamedQuery(name="Address.findAll", query="SELECT a FROM Address a")
public class Address implements Serializable {

	private static final long serialVersionUID = 2339974563902001678L;

	@Id
	@Column(name = "user_id", unique = true, nullable = false, length = 8)
	private String userId;

	@Column(name = "address")
	private String address;

	@Column(name="last_updated_date")
	private Timestamp lastUpdatedDate;

    @Column(name = "ver")
	private Integer ver;

    @Column(name = "zip_cd", length = 8)
	private String zipCd;

	@OneToOne(optional=false, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
	private User usr;

	public Address() {
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getLastUpdatedDate() {
		return this.lastUpdatedDate;
	}

	public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public Integer getVer() {
		return this.ver;
	}

	public void setVer(Integer ver) {
		this.ver = ver;
	}

	public String getZipCd() {
		return this.zipCd;
	}

	public void setZipCd(String zipCd) {
		this.zipCd = zipCd;
	}

	public User getUsr() {
		return this.usr;
	}

	public void setUsr(User usr) {
		this.usr = usr;
	}

}