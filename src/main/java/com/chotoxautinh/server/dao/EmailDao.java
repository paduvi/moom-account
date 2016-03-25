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
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

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
