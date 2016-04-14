/**
 * @author chotoxautinh
 *
 * Mar 23, 2016 - http://chotoxautinh.com/
 */
package com.chotoxautinh.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.chotoxautinh.server.model.FaceAccount;

@Repository
public interface FaceAccountRepository
		extends MongoRepository<FaceAccount, String>, QueryDslPredicateExecutor<FaceAccount> {

	FaceAccount findById(String id);

	FaceAccount findByEmail(String email);
}
