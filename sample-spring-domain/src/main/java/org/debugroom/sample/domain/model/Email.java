package org.debugroom.sample.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author org.debugroom
 *
 */
@Entity
@Table(name = "EMAIL")
public class Email implements Serializable{

	private static final long serialVersionUID = -4574344780115697003L;
	
	private EmailPK emailPK;
	private String email;
	private User user;
	
	@EmbeddedId
	public EmailPK getEmailPK() {
		return emailPK;
	}
	public void setEmailPK(EmailPK emailPK) {
		this.emailPK = emailPK;
	}
	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@ManyToOne
	@JoinColumn(name = "USER_ID", insertable=false, updatable=false)
	@JsonIgnore
	public User getUser() {
		return user;
	}
	@JsonIgnore
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString(){
		return new ToStringBuilder(this)
					.append("emailPK : ", getEmailPK())
					.append("email : ", getEmail())
					.toString();
	}

	@Override
	public boolean equals(Object other){
		if(this == other){
			return true;
		}
		if(!(other instanceof Email)){
			return false;
		}
		Email email = (Email)other;
		return new EqualsBuilder()
					.append(this.getEmailPK(), email.getEmailPK())
					.isEquals();
	}
	
	@Override
	public int hashCode(){
		return new HashCodeBuilder()
					.append(getEmailPK())
					.toHashCode();
	}

}
