/**
 * @author chotoxautinh
 *
 * Mar 23, 2016 - http://chotoxautinh.com/
 */
package com.chotoxautinh.server.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chotoxautinh.server.service.Counter;

@Repository
public interface CounterRepository extends QueryDslPredicateExecutor<Counter>, CrudRepository<Counter, String>  {

	Counter findById(String id);
}
