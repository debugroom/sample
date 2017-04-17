package org.debugroom.sample.cassandra.pattern2.domain.entity;

import java.util.Date;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.UserDefinedType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
@UserDefinedType
public class Email {

	@Column("email")
	private String email;
	@Column("ver")
	private int ver;
	@Column("last_updated_date")
	private Date lastUpdatedDate;
	

}
