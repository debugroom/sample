package org.debugroom.sample.spring.jpa.domain.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.NamedQuery;

import lombok.Builder;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Builder
@Entity
@Table(name = "affiliation", schema = "public")
@NamedQuery(name="Affiliation.findAll", query="SELECT a FROM Affiliation a")
public class Affiliation implements Serializable {
	
	private static final long serialVersionUID = 3832620537736917777L;

	@EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "groupId", column = @Column(name = "group_id", nullable = false, length = 10) ),
        @AttributeOverride(name = "userId", column = @Column(name = "user_id", nullable = false, length = 8) ) })
	private AffiliationPK id;

	@Column(name="last_updated_date")
	private Timestamp lastUpdatedDate;

    @Column(name = "ver")
	private Integer ver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false, insertable = false, updatable = false)
	private Group grp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
	private User usr;

	public Affiliation() {
	}

	public AffiliationPK getId() {
		return this.id;
	}

	public void setId(AffiliationPK id) {
		this.id = id;
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

	public Group getGrp() {
		return this.grp;
	}

	public void setGrp(Group grp) {
		this.grp = grp;
	}

	public User getUsr() {
		return this.usr;
	}

	public void setUsr(User usr) {
		this.usr = usr;
	}

}