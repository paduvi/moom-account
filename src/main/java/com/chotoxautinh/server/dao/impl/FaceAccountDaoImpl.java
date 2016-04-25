/**
 * @author chotoxautinh
 *
 * Mar 23, 2016 - http://chotoxautinh.com/
 */
package com.chotoxautinh.server.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.google.common.collect.Lists;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;

@Component
public class FaceAccountDaoImpl implements FaceAccountDao {

	static Logger logger = LoggerFactory.getLogger(FaceAccountDaoImpl.class);

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
	public boolean addFaceAccount(FaceAccount faccount) {
		if (repository.findByEmail(faccount.getEmail()) != null) {
			logger.error("Duplicate Email: " + faccount.getEmail());
			return false;
		}
		try {
			faccount.setId(String.valueOf(counterDao.getNextSequence(collectionName)));
			repository.save(faccount);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.chotoxautinh.dao.FaceAccountDao#updateFaceAccount(com.chotoxautinh.
	 * model.FaceAccount)
	 */
	@Override
	public boolean updateFaceAccount(FaceAccount faccount) {
		try {
			repository.save(faccount);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.chotoxautinh.dao.FaceAccountDao#removeFaceAccount(java.lang.String)
	 */
	@Override
	public boolean removeFaceAccount(String id) {
		try {
			FaceAccount existingFaceAccount = findFaceAccountById(id);

			if (existingFaceAccount == null) {
				logger.error("Account not existed");
				return false;
			}
			groupDao.decnAccounts(existingFaceAccount.getGroup());
			repository.delete(existingFaceAccount);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.chotoxautinh.dao.FaceAccountDao#removeFaceAccount(com.chotoxautinh.
	 * model.FaceAccount)
	 */
	@Override
	public boolean removeFaceAccount(FaceAccount faccount) {
		return removeFaceAccount(faccount.getId());
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chotoxautinh.dao.FaceAccountDao#addFaceAccountToGroup(com.
	 * chotoxautinh.model.FaceAccount, com.chotoxautinh.model.Group)
	 */
	@Override
	public boolean addFaceAccountToGroup(FaceAccount faccount, Group group) {
		return addFaceAccountToGroup(faccount, group.getId());
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
	 * @see com.chotoxautinh.dao.FaceAccountDao#addFaceAccountToGroup(com.
	 * chotoxautinh.model.FaceAccount, java.lang.String)
	 */
	@Override
	public boolean addFaceAccountToGroup(FaceAccount faccount, String groupId) {
		if (faccount.getGroup() != null && faccount.getGroup().equals(groupId))
			return false;
		faccount.setGroup(groupId);
		groupDao.incnAccounts(groupId);
		return updateFaceAccount(faccount);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chotoxautinh.dao.FaceAccountDao#findAllFaceAccounts()
	 */
	@Override
	public List<FaceAccount> findAllFaceAccounts() {
		return Lists.newLinkedList(repository.findAll());
	}

//	@Override
//	public FaceAccount findFaceAccount(Predicate predicate) {
//		return repository.findOne(predicate);
//	}
//	
//	@Override
//	public List<FaceAccount> findAllFaceAccounts(Predicate predicate) {
//		return Lists.newLinkedList(repository.findAll(predicate));
//	}
//
//	@Override
//	public List<FaceAccount> findAllFaceAccounts(Predicate predicate, OrderSpecifier<?>... orders) {
//		return Lists.newLinkedList(repository.findAll(predicate, orders));
//	}
//
//	@Override
//	public Page<FaceAccount> findFaceAccountsByPage(Predicate predicate, Pageable page) {
//		return repository.findAll(predicate, page);
//	}
//
//	@Override
//	public Long count(Predicate predicate) {
//		return repository.count(predicate);
//	}
//
//	/* (non-Javadoc)
//	 * @see com.chotoxautinh.server.dao.FaceAccountDao#findFaceAccountsByPage(org.springframework.data.domain.Pageable)
//	 */
//	@Override
//	public Page<FaceAccount> findFaceAccountsByPage(Pageable page) {
//		return findFaceAccountsByPage(null, page);
//	}
}
