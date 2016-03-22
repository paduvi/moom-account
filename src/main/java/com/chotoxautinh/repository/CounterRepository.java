package com.chotoxautinh.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.chotoxautinh.service.Counter;

/**
 * @author chotoxautinh
 *
 * http://chotoxautinh.com/
 * Mar 23, 2016
 */
public interface CounterRepository extends MongoRepository<Counter, String> {

	Counter findById(String id);
	
}
