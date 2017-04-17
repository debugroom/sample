package org.debugroom.sample.cassandra.pattern1.domain.entity;

import java.util.Date;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
@Table("credential")
public class Credential {

	public Credential(){
	}

	@PrimaryKey
	private CredentialPK credentialPK;
	@Column("credential_key")
	private String credentialKey;
	@Column("user_id")
	private Long userId;
	@Column("expired_date")
	private Date expiredDate;
	@Column("ver")
	private int ver;
	@Column("last_updated_date")
	private Date lastUpdatedDate;

}
