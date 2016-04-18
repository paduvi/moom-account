/**
 * @author chotoxautinh
 *
 * Mar 23, 2016 - http://chotoxautinh.com/
 */
package com.chotoxautinh.server.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chotoxautinh.server.model.Email;
import com.chotoxautinh.server.model.FaceAccount;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;

@Repository
public interface FaceAccountRepository extends  CrudRepository<FaceAccount, String>, QueryDslPredicateExecutor<FaceAccount> {
	FaceAccount findById(String id);
	FaceAccount findByEmail(String email);
}
