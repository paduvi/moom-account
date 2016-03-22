package com.chotoxautinh.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.chotoxautinh.model.Email;

/**
 * @author chotoxautinh
 *
 * http://chotoxautinh.com/
 * Mar 23, 2016
 */
public interface EmailRepository extends MongoRepository<Email, String>, QueryDslPredicateExecutor<Email> {

	Email findById(String id);
}
