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
public class Credential {

	@Column("credential_type")
	private String credentialType;
	@Column("credential_key")
	private String credentialKey;
	@Column("expired_date")
	private Date expiredDate;
	@Column("ver")
	private int ver;
	@Column("last_updated_date")
	private Date lastUpdatedDate;

}
