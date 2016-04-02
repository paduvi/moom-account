/**
 * @author chotoxautinh
 *
 * Mar 23, 2016 - http://chotoxautinh.com/
 */
package com.chotoxautinh.server.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.chotoxautinh.server.dao.CounterDao;
import com.chotoxautinh.server.dao.EmailDao;
import com.chotoxautinh.server.model.Email;
import com.chotoxautinh.server.repository.EmailRepository;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

@Component
public class EmailDaoImpl implements EmailDao {

	public static final String collectionName = "emails";
	@Autowired
	private CounterDao counterDao;

	@Autowired
	private EmailRepository repository;

	@Override
	public boolean addEmail(Email email) {
		try {
			if (repository.findByUsername(email.getUsername()) != null)
				return false;
			email.setId(String.valueOf(counterDao.getNextSequence(collectionName)));
			repository.save(email);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateEmail(Email email) {
		try {
			repository.save(email);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean removeEmail(String id) {
		try {
			Email existingEmail = findEmailById(id);

			if (existingEmail == null) {
				return false;
			}
			repository.delete(existingEmail);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean removeEmail(Email email) {
		return removeEmail(email.getId());
	}

	@Override
	public Email findEmailById(String id) {
		return repository.findById(id);
	}

	@Override
	public Email findEmail(Predicate predicate) {
		return repository.findOne(predicate);
	}

	@Override
	public List<Email> findAllEmails() {
		return repository.findAll();
	}

	@Override
	public Iterable<Email> findAllEmails(Predicate predicate) {
		return repository.findAll(predicate);
	}

	@Override
	public Iterable<Email> findAllEmails(Predicate predicate, OrderSpecifier<?>... orders) {
		return repository.findAll(predicate, orders);
	}

	@Override
	public Page<Email> findEmailsByPage(Pageable page) {
		return repository.findAll(page);
	}

	@Override
	public Page<Email> findEmailsByPage(Predicate predicate, Pageable page) {
		return repository.findAll(predicate, page);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chotoxautinh.server.dao.EmailDao#count(com.mysema.query.types.
	 * Predicate)
	 */
	@Override
	public Long count(Predicate predicate) {
		return repository.count(predicate);
	}

}
