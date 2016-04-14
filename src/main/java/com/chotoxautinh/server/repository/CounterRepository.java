/**
 * @author chotoxautinh
 *
 * Mar 23, 2016 - http://chotoxautinh.com/
 */
package com.chotoxautinh.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.chotoxautinh.server.service.Counter;

@Repository
public interface CounterRepository extends MongoRepository<Counter, String> {

	Counter findById(String id);
	
}
