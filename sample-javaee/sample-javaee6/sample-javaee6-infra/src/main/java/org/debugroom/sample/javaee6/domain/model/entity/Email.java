package org.debugroom.sample.javaee6.domain.model.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;

import lombok.Builder;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Builder

/**
 * The persistent class for the email database table.
 * 
 */
@Entity
@NamedQuery(name="Email.findAll", query="SELECT e FROM Email e")
public class Email implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EmailPK id;

	private String email;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="company_id", referencedColumnName="company_id", insertable=false, updatable=false),
		@JoinColumn(name="user_id", referencedColumnName="user_id", insertable=false, updatable=false)
		})
	@JsonBackReference
	private User usr;

	public Email() {
	}

	public EmailPK getId() {
		return this.id;
	}

	public void setId(EmailPK id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User getUsr() {
		return this.usr;
	}

	public void setUsr(User usr) {
		this.usr = usr;
	}

}