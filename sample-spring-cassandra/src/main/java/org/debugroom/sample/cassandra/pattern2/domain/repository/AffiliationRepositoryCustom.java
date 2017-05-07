package org.debugroom.sample.cassandra.pattern2.domain.repository;

import java.util.List;
import java.util.Map;

public interface AffiliationRepositoryCustom {

	public Map<Long, List<Long>> findGroupIdsMapByUserId();
	
}
