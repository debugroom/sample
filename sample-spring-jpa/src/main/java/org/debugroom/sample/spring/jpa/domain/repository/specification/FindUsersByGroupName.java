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
public class FindUsersByGroupName implements Specification<User>{
	
	private String groupName;

	@Override
	public Predicate toPredicate(Root<User> root, 
			CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		List<Predicate> predicates = new ArrayList<Predicate>();
		Join<User, Affiliation> joinAffiliation = root.join(User_.affiliations);
		Join<Affiliation, Group> joinGroup = joinAffiliation.join(Affiliation_.grp);
		predicates.add(criteriaBuilder.equal(joinGroup.get(Group_.groupName), groupName));

		return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
	}

}
