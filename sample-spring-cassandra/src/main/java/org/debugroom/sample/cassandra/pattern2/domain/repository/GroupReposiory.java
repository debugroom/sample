package org.debugroom.sample.cassandra.pattern2.domain.repository;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.debugroom.sample.cassandra.pattern2.domain.entity.Group;

public interface GroupReposiory extends CrudRepository<Group, Long>{
}
