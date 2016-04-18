/**
 * @author chotoxautinh
 *
 * Apr 2, 2016 - http://chotoxautinh.com/
 */
package com.chotoxautinh.server.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chotoxautinh.server.model.Group;
import com.chotoxautinh.server.model.User;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;

@Repository
public interface UserRepository extends  CrudRepository<User, String>, QueryDslPredicateExecutor<User> {

	User findById(String id);
	User findByUsername(String username);
}