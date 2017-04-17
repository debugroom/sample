package org.debugroom.sample.cassandra.pattern1.domain.entity;

import java.io.Serializable;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
@PrimaryKeyClass
public class CredentialPK implements Serializable{

	private static final long serialVersionUID = -1455847362077001018L;

	@PrimaryKeyColumn(name = "login_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private String loginId;
    @PrimaryKeyColumn(name = "credential_type", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
	private String credentialType;

}
