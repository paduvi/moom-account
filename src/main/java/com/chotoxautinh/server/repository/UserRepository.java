/**
 * @author chotoxautinh
 *
 * Apr 2, 2016 - http://chotoxautinh.com/
 */
package com.chotoxautinh.server.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chotoxautinh.server.model.User;

@Repository
public interface UserRepository extends  CrudRepository<User, String>, QueryDslPredicateExecutor<User> {

	User findById(String id);
	User findByUsername(String username);
}