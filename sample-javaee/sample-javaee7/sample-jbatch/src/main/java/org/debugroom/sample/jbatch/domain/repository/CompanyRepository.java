package org.debugroom.sample.jbatch.domain.repository;

import org.debugroom.sample.jbatch.domain.model.entity.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, String>{
}
