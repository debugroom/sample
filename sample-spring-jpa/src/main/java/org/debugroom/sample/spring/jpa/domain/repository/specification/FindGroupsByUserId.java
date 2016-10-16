package org.debugroom.sample.spring.jpa.domain.repository.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.debugroom.sample.spring.jpa.domain.entity.Affiliation;
import org.debugroom.sample.spring.jpa.domain.entity.Affiliation_;
import org.debugroom.sample.spring.jpa.domain.entity.Group;
import org.debugroom.sample.spring.jpa.domain.entity.Group_;
import org.debugroom.sample.spring.jpa.domain.entity.User;
import org.debugroom.sample.spring.jpa.domain.entity.User_;
import org.springframework.data.jpa.domain.Specification;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
@Data
public class FindGroupsByUserId implements Specification<Group>{

	private String userId;
	
	@Override
	public Predicate toPredicate(Root<Group> root, 
			CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		Join<Group, Affiliation> joinAffiliation = root.join(Group_.affiliations);
		Join<Affiliation, User> joinUser = joinAffiliation.join(Affiliation_.usr);
		predicates.add(criteriaBuilder.equal(joinUser.get(User_.userId), userId));
		
		return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));

	}

}
