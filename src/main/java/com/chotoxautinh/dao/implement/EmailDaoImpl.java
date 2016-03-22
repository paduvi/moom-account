package com.chotoxautinh.dao.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.chotoxautinh.dao.CounterDao;
import com.chotoxautinh.dao.EmailDao;
import com.chotoxautinh.model.Email;
import com.chotoxautinh.repository.EmailRepository;
import com.chotoxautinh.service.CounterException;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

public class EmailDaoImpl implements EmailDao {

	public static final String collectionName = "emails";
	@Autowired
	private CounterDao counterDao;

	@Autowired
	private EmailRepository repository;

	@Override
	public Email addEmail(Email email) {
		email.setId(String.valueOf(counterDao.getNextSequence(collectionName)));
		return repository.save(email);
	}

	@Override
	public Email updateEmail(Email email) {
		return repository.save(email);
	}

	@Override
	public void removeEmail(String id) {
		Email existingEmail = repository.findById(id);

		if (existingEmail == null) {
			throw new CounterException("Unable to get email for id : " + id);
		}
		repository.delete(existingEmail);
	}

	@Override
	public void removeEmail(Email email) {
		repository.delete(email);
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
	public Iterable<Email> findAllEmails() {
		return (Iterable<Email>) repository.findAll();
	}

	@Override
	public Iterable<Email> findAllEmails(Predicate predicate) {
		if(predicate == null){
			return findAllEmails();
		}
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

}
