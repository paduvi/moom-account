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
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.chotoxautinh.server.dao.CounterDao;
import com.chotoxautinh.server.dao.GroupDao;
import com.chotoxautinh.server.model.Group;
import com.chotoxautinh.server.repository.GroupRepository;
import com.chotoxautinh.server.service.CounterException;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

@Component
public class GroupDaoImpl implements GroupDao {

	static Logger logger = LoggerFactory.getLogger(GroupDaoImpl.class);
	
	public static final String collectionName = "groups";
	@Autowired
	private CounterDao counterDao;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private GroupRepository repository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chotoxautinh.dao.GroupDao#addGroup(com.chotoxautinh.model.Group)
	 */
	@Override
	public boolean addGroup(Group group) {
		try {
			group.setId(String.valueOf(counterDao.getNextSequence(collectionName)));
			group.setLastExecution(System.currentTimeMillis());
			repository.save(group);
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
	 * com.chotoxautinh.dao.GroupDao#updateGroup(com.chotoxautinh.model.Group)
	 */
	@Override
	public boolean updateGroup(Group group) {
		try {
			group.setLastExecution(System.currentTimeMillis());
			repository.save(group);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chotoxautinh.dao.GroupDao#incnAccounts(java.lang.String)
	 */
	@Override
	public boolean incnAccounts(String id) {
		return changenAccounts(id, 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chotoxautinh.dao.GroupDao#decnAccounts(java.lang.String)
	 */
	@Override
	public boolean decnAccounts(String id) {
		return changenAccounts(id, -1);
	}

	private boolean changenAccounts(String id, int number) {
		// get group id
		Query query = new Query(Criteria.where("_id").is(id));

		// increase sequence id by 1
		Update update = new Update();
		update.inc("nAccounts", number);
		update.set("lastExecution", System.currentTimeMillis());

		// return new increased id
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);

		// this is the magic happened.
		Group group = mongoTemplate.findAndModify(query, update, options, Group.class);

		// if no id, throws SequenceException
		// optional, just a way to tell user when the sequence id is failed to
		// generate.
		if (group == null) {
			logger.error("Unable to get group document for id : " + id);
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chotoxautinh.dao.GroupDao#removeGroup(java.lang.String)
	 */
	@Override
	public boolean removeGroup(String id) {
		try {
			Group existingGroup = repository.findById(id);

			if (existingGroup == null) {
				throw new CounterException("Unable to get group for id : " + id);
			}
			repository.delete(existingGroup);
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
	 * com.chotoxautinh.dao.GroupDao#removeGroup(com.chotoxautinh.model.Group)
	 */
	@Override
	public boolean removeGroup(Group group) {
		return removeGroup(group.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chotoxautinh.dao.GroupDao#findGroupById(java.lang.String)
	 */
	@Override
	public Group findGroupById(String id) {
		Group group = repository.findById(id);
		updateGroup(group);
		return group;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chotoxautinh.dao.GroupDao#findGroupByName(java.lang.String)
	 */
	@Override
	public Group findGroupByName(String name) {
		Group group = repository.findByName(name);
		updateGroup(group);
		return group;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.chotoxautinh.dao.GroupDao#findGroup(com.mysema.query.types.Predicate)
	 */
	@Override
	public Group findGroup(Predicate predicate) {
		Group group = repository.findOne(predicate);
		updateGroup(group);
		return group;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chotoxautinh.dao.GroupDao#findAllGroups()
	 */
	@Override
	public List<Group> findAllGroups() {
		return repository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chotoxautinh.dao.GroupDao#findAllGroups(com.mysema.query.types.
	 * Predicate)
	 */
	@Override
	public Iterable<Group> findAllGroups(Predicate predicate) {
		return repository.findAll(predicate);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chotoxautinh.dao.GroupDao#findAllGroups(com.mysema.query.types.
	 * Predicate, com.mysema.query.types.OrderSpecifier[])
	 */
	@Override
	public Iterable<Group> findAllGroups(Predicate predicate, OrderSpecifier<?>... orders) {
		return repository.findAll(predicate, orders);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.chotoxautinh.dao.GroupDao#findGroupsByPage(org.springframework.data.
	 * domain.Pageable)
	 */
	@Override
	public Page<Group> findGroupsByPage(Pageable page) {
		return repository.findAll(page);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.chotoxautinh.dao.GroupDao#findGroupsByPage(com.mysema.query.types.
	 * Predicate, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<Group> findGroupsByPage(Predicate predicate, Pageable page) {
		return repository.findAll(predicate, page);
	}

}
