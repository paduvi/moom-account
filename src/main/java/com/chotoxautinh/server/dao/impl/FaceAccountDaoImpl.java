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
	public boolean addFaceAccount(FaceAccount faccount) {
		if (repository.findByEmail(faccount.getEmail()) != null)
			return false;
		try {
			faccount.setId(String.valueOf(counterDao.getNextSequence(collectionName)));
			repository.save(faccount);
			return true;
		} catch (Exception e) {
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
				return false;
			}
			groupDao.decnAccounts(existingFaceAccount.getGroup());
			repository.delete(existingFaceAccount);
			return true;
		} catch (Exception e) {
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
	public boolean addFaceAccountToGroup(FaceAccount faccount, Group group) {
		return addFaceAccountToGroup(faccount, group.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chotoxautinh.dao.FaceAccountDao#addFaceAccountToGroup(com.
	 * chotoxautinh.model.FaceAccount, java.lang.String)
	 */
	@Override
	public boolean addFaceAccountToGroup(FaceAccount faccount, String groupId) {
		if (faccount.getGroup().equals(groupId))
			return false;
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
