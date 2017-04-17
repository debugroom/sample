package org.debugroom.sample.cassandra.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Data
@NoArgsConstructor
@Table("users")
public class User {

	@PrimaryKey("user_id") private Long userId;

	@Column("user_name") private String userName;
	@Column("login_id") private String loginId;

	public User(Long userId) {
		this.setUserId(userId);
	}

}
