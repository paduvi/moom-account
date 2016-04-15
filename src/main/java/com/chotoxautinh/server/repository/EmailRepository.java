/**
 * @author chotoxautinh
 *
 * Mar 23, 2016 - http://chotoxautinh.com/
 */
package com.chotoxautinh.server.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chotoxautinh.server.model.Email;

@Repository
public interface EmailRepository extends QueryDslPredicateExecutor<Email>, CrudRepository<Email, String> {

	Email findById(String id);
	Email findByUsername(String username);
}
