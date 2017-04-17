package org.debugroom.sample.cassandra.pattern2.domain.entity;

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
public class AffiliationPK implements Serializable{

	private static final long serialVersionUID = 6634695143792297552L;

	@PrimaryKeyColumn(name = "user_id", ordinal = 0, type = PrimaryKeyType.CLUSTERED)
    private Long userId;
    @PrimaryKeyColumn(name = "group_id", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private Long groupId;

}
