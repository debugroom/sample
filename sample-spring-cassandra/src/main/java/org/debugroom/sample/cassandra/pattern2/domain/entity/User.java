package org.debugroom.sample.cassandra.pattern2.domain.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.mapping.Table;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
@Table("users")
public class User {

	public User(){
	}

	@PrimaryKey("user_id")
	private Long userId;
	@Column("user_name")
	private String userName;
	@Column("login_id")
	private String loginId;
	@Column("credentials")
	private List<Credential> credentials;
	@Column("is_enabled")
	private boolean isEnabled;
	@Column("is_locked")
	private boolean isLocked;
	@Column("is_admin")
	private boolean isAdmin;
	@Column("address")
	private Address address;
	@Column("emails")
	List<Email> emails;
	@Column("ver")
	private int ver;
	@Column("last_updated_date")
	private Date lastUpdatedDate;
	@Transient
	private List<Group> groups;
	
}
