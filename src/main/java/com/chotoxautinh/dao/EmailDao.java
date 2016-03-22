package com.chotoxautinh.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.chotoxautinh.model.Email;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

/**
 * @author chotoxautinh
 *
 * http://chotoxautinh.com/
 * Mar 23, 2016
 */
public interface EmailDao {
	Email addEmail(Email email);

	Email updateEmail(Email email);
	
	void removeEmail(String id);
	
	void removeEmail(Email email);

	Email findEmailById(String id);

	Email findEmail(Predicate predicate);

	List<Email> findAllEmails();
	
	Iterable<Email> findAllEmails(Predicate predicate);
	
	Iterable<Email> findAllEmails(Predicate predicate, OrderSpecifier<?>... orders);
	
	Page<Email> findEmailsByPage(Pageable page);

	Page<Email> findEmailsByPage(Predicate predicate, Pageable page);
}
