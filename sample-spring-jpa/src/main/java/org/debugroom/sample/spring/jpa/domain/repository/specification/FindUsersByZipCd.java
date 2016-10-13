package org.debugroom.sample.spring.jpa.domain.repository.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Join;

import org.debugroom.sample.spring.jpa.domain.entity.Address;
import org.debugroom.sample.spring.jpa.domain.entity.Address_;
import org.debugroom.sample.spring.jpa.domain.entity.User;
import org.debugroom.sample.spring.jpa.domain.entity.User_;
import org.springframework.data.jpa.domain.Specification;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
@Data
public class FindUsersByZipCd implements Specification<User>{

	private String zipCd;

	@Override
	public Predicate toPredicate(Root<User> root, 
			CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		List<Predicate> predicates = new ArrayList<Predicate>();
		
		Join<User, Address> joinAddress = root.join(User_.address);
		predicates.add(criteriaBuilder.equal(joinAddress.get(Address_.zipCd), zipCd));
		
		return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
	}
	
	
}
