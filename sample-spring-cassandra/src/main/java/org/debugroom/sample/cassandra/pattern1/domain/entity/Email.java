package org.debugroom.sample.cassandra.pattern1.domain.entity;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Date;

import org.springframework.data.cassandra.mapping.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
@Table("email")
public class Email {

	public Email(){
	}

	@PrimaryKey("email")
	private String email;
	@Column("user_id")
	private Long userId;
	@Column("ver")
	private int ver;
	@Column("last_updated_date")
	private Date lastUpdatedDate;

}
