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
import com.chotoxautinh.server.dao.FaceAccountDao;
import com.chotoxautinh.server.dao.GroupDao;
import com.chotoxautinh.server.model.FaceAccount;
import com.chotoxautinh.server.model.Group;
import com.chotoxautinh.server.repository.FaceAccountRepository;
import com.chotoxautinh.server.service.CounterException;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

@Component
public class FaceAccountDaoImpl implements FaceAccountDao {

	public static final String collectionName = "faccounts";
	@Autowired
	private CounterDao counterDao;

	@Autowired
	private GroupDao groupDao;

	@Autowired
	private FaceAccountRepository repository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.chotoxautinh.dao.FaceAccountDao#addFaceAccount(com.chotoxautinh.model
	 * .FaceAccount)
	 */
	@Override
	public FaceAccount addFaceAccount(FaceAccount faccount) {
		faccount.setId(String.valueOf(counterDao.getNextSequence(collectionName)));
		return repository.save(faccount);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.chotoxautinh.dao.FaceAccountDao#updateFaceAccount(com.chotoxautinh.
	 * model.FaceAccount)
	 */
	@Override
	public FaceAccount updateFaceAccount(FaceAccount faccount) {
		return repository.save(faccount);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.chotoxautinh.dao.FaceAccountDao#removeFaceAccount(java.lang.String)
	 */
	@Override
	public void removeFaceAccount(String id) {
		FaceAccount existingFaceAccount = repository.findById(id);

		if (existingFaceAccount == null) {
			throw new CounterException("Unable to get email for id : " + id);
		}
		groupDao.decnAccounts(existingFaceAccount.getGroup());
		repository.delete(existingFaceAccount);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.chotoxautinh.dao.FaceAccountDao#removeFaceAccount(com.chotoxautinh.
	 * model.FaceAccount)
	 */
	@Override
	public void removeFaceAccount(FaceAccount faccount) {
		groupDao.decnAccounts(faccount.getGroup());
		repository.delete(faccount);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.chotoxautinh.dao.FaceAccountDao#findFaceAccountById(java.lang.String)
	 */
	@Override
	public FaceAccount findFaceAccountById(String id) {
		return repository.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.chotoxautinh.dao.FaceAccountDao#findFaceAccount(com.mysema.query.
	 * types.Predicate)
	 */
	@Override
	public FaceAccount findFaceAccount(Predicate predicate) {
		return repository.findOne(predicate);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chotoxautinh.dao.FaceAccountDao#findAllFaceAccounts()
	 */
	@Override
	public List<FaceAccount> findAllFaceAccounts() {
		return repository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.chotoxautinh.dao.FaceAccountDao#findAllFaceAccounts(com.mysema.query.
	 * types.Predicate)
	 */
	@Override
	public Iterable<FaceAccount> findAllFaceAccounts(Predicate predicate) {
		return repository.findAll(predicate);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.chotoxautinh.dao.FaceAccountDao#findAllFaceAccounts(com.mysema.query.
	 * types.Predicate, com.mysema.query.types.OrderSpecifier[])
	 */
	@Override
	public Iterable<FaceAccount> findAllFaceAccounts(Predicate predicate, OrderSpecifier<?>... orders) {
		return repository.findAll(predicate, orders);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chotoxautinh.dao.FaceAccountDao#findFaceAccountsByPage(org.
	 * springframework.data.domain.Pageable)
	 */
	@Override
	public Page<FaceAccount> findFaceAccountsByPage(Pageable page) {
		return repository.findAll(page);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.chotoxautinh.dao.FaceAccountDao#findFaceAccountsByPage(com.mysema.
	 * query.types.Predicate, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<FaceAccount> findFaceAccountsByPage(Predicate predicate, Pageable page) {
		return repository.findAll(predicate, page);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chotoxautinh.dao.FaceAccountDao#addFaceAccountToGroup(com.
	 * chotoxautinh.model.FaceAccount, com.chotoxautinh.model.Group)
	 */
	@Override
	public FaceAccount addFaceAccountToGroup(FaceAccount faccount, Group group) {
		return addFaceAccountToGroup(faccount, group.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chotoxautinh.dao.FaceAccountDao#addFaceAccountToGroup(com.
	 * chotoxautinh.model.FaceAccount, java.lang.String)
	 */
	@Override
	public FaceAccount addFaceAccountToGroup(FaceAccount faccount, String groupId) {
		faccount.setGroup(groupId);
		groupDao.incnAccounts(groupId);
		return updateFaceAccount(faccount);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.chotoxautinh.server.dao.FaceAccountDao#count(com.mysema.query.types.
	 * Predicate)
	 */
	@Override
	public Long count(Predicate predicate) {
		return repository.count(predicate);
	}
}
