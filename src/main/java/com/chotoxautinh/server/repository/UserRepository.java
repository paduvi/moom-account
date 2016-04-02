/**
 * @author chotoxautinh
 *
 * Apr 2, 2016 - http://chotoxautinh.com/
 */
package com.chotoxautinh.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.chotoxautinh.server.model.User;

public interface UserRepository extends MongoRepository<User, String>, QueryDslPredicateExecutor<User> {

	User findById(String id);
	
	User findByUsername(String username);
}