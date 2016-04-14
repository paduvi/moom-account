/**
 * @author chotoxautinh
 *
 * Mar 23, 2016 - http://chotoxautinh.com/
 */
package com.chotoxautinh.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.chotoxautinh.server.model.Group;

@Repository
public interface GroupRepository extends MongoRepository<Group, String>, QueryDslPredicateExecutor<Group> {

	Group findById(String id);

	Group findByName(String name);
}
