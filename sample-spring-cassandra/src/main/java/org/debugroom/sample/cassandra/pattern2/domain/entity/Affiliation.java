package org.debugroom.sample.cassandra.pattern2.domain.entity;

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
@Table("affiliation")
public class Affiliation {

	public Affiliation(){
	}

	@PrimaryKey
	private AffiliationPK affiliationPK;
	@Column("ver")
	private int ver;
	@Column("last_updated_date")
	private Date lastUpdatedDate;
	
}
