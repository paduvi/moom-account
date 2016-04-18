/**
 * @author chotoxautinh
 *
 * Apr 2, 2016 - http://chotoxautinh.com/
 */
package com.chotoxautinh.server.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.chotoxautinh.server.model.User;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;

public interface UserDao {
	boolean createUser(User user);

	boolean updateUser(User user);
	
	boolean removeUser(String id);
	
	boolean removeUser(User user);

	User findUserById(String id);
	
	User findUserByUsername(String name);

	User findUser(Predicate predicate);
	
	List<User> findAllUsers();
	
	List<User> findAllUsers(Predicate predicate);
	
	List<User> findAllUsers(Predicate predicate, OrderSpecifier<?>... orders);
	
	Page<User> findUsersByPage(Pageable page);
	
	Page<User> findUsersByPage(Predicate predicate, Pageable page);
	
	Long count(Predicate predicate);
	
}
