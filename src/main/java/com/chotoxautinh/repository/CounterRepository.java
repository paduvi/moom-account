package com.chotoxautinh.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.chotoxautinh.service.Counter;

public interface CounterRepository extends MongoRepository<Counter, String>, QueryDslPredicateExecutor<Counter> {

	Counter findById(String id);
}
