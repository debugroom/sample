package org.debugroom.sample.spring.jpa.domain.repository.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;

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
public class FindUsersByNotZipCd implements Specification<User>{
	
	private String zipCd;

	@Override
	public Predicate toPredicate(Root<User> root, 
			CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		
		Path<Object> path = root.get("userId");
		Subquery<User> subQuery = criteriaBuilder.createQuery().subquery(User.class);
		Root<User> subQueryRoot = subQuery.from(User.class);
		Join<User, Address> subQueryJoinAddress = subQueryRoot.join(User_.address);
		Predicate subQueryPredicate = criteriaBuilder.equal(
				subQueryJoinAddress.get(Address_.zipCd), zipCd);
		subQuery.select(subQueryRoot.get("userId"));
		subQuery.where(subQueryPredicate);
		
		return criteriaBuilder.not(criteriaBuilder.in(path).value(subQuery));
	}
}
