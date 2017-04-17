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
public class AddressPK implements Serializable{

	public AddressPK(){}
	private static final long serialVersionUID = -828112214783052932L;

    @PrimaryKeyColumn(name = "zip_cd", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private String zipCd;
	@PrimaryKeyColumn(name = "user_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
	private Long userId;
	
}
