/**
 * @author chotoxautinh
 *
 * Mar 23, 2016 - http://chotoxautinh.com/
 */
package com.chotoxautinh.server.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.chotoxautinh.server.model.Email;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;

public interface EmailDao {
	boolean addEmail(Email email);

	boolean updateEmail(Email email);
	
	boolean removeEmail(String id);
	
	boolean removeEmail(Email email);

	Email findEmailById(String id);

	Email findEmail(Predicate predicate);
	
	List<Email> findAllEmails();
	
	List<Email> findAllEmails(Predicate predicate);
	
	List<Email> findAllEmails(Predicate predicate, OrderSpecifier<?>... orders);
	
	Page<Email> findEmailsByPage(Predicate predicate, Pageable page);
	
	Long count(Predicate predicate);
	
}
