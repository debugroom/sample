package org.debugroom.sample.spring.jpa.domain.repository.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

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
public class FindUsersByNotGroupName implements Specification<User>{
	
	private String groupName;
	
	@Override
	public Predicate toPredicate(Root<User> root, 
			CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		
		Path<Object> path = root.get("userId");
		Subquery<User> subQuery = criteriaBuilder.createQuery().subquery(User.class);
		Root<User> subQueryRoot = subQuery.from(User.class);
		Join<User, Affiliation> subQueryJoinAffiliation = subQueryRoot.join(User_.affiliations);
		Join<Affiliation, Group> subQueryJoinGroup = subQueryJoinAffiliation.join(Affiliation_.grp);
		Predicate subQueryPredicate = criteriaBuilder.equal(
				subQueryJoinGroup.get(Group_.groupName), groupName);
		subQuery.select(subQueryRoot.get("userId"));
		subQuery.where(subQueryPredicate);
		
		return criteriaBuilder.not(criteriaBuilder.in(path).value(subQuery));
	}

}
