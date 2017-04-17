package org.debugroom.sample.cassandra.pattern1.domain.entity;

import java.util.Date;

import java.util.List;

import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.UserDefinedType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
@UserDefinedType
public class UserOfGroup{
	public UserOfGroup(){}
	@PrimaryKey("user_id")
	private Long userId;
	@Column("user_name")
	private String userName;
	@Column("is_enabled")
	private boolean isEnabled;
	@Column("is_locked")
	private boolean isLocked;
	@Column("is_admin")
	private boolean isAdmin;
	@Column("ver")
	private int ver;
	@Column("last_updated_date")
	private Date lastUpdatedDate;	
}
