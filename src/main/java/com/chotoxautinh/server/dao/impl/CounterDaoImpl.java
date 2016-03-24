/**
 * @author chotoxautinh
 *
 * Mar 23, 2016 - http://chotoxautinh.com/
 */
package com.chotoxautinh.server.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.chotoxautinh.server.dao.CounterDao;
import com.chotoxautinh.server.repository.CounterRepository;
import com.chotoxautinh.server.service.Counter;
import com.chotoxautinh.server.service.CounterException;

@Component
public class CounterDaoImpl implements CounterDao {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private CounterRepository repository;

	@Override
	public int getNextSequence(String collectionName) throws CounterException {

		// get sequence id
		Query query = new Query(Criteria.where("_id").is(collectionName));

		// increase sequence id by 1
		Update update = new Update();
		update.inc("seq", 1);

		// return new increased id
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);

		// this is the magic happened.
		Counter seqId = mongoTemplate.findAndModify(query, update, options, Counter.class);

		// if no id, throws SequenceException
		// optional, just a way to tell user when the sequence id is failed to
		// generate.
		if (seqId == null) {
			throw new CounterException("Unable to get sequence id for collection : " + collectionName);
		}
		return seqId.getSeq();

	}

	private Counter findByCounterId(String id) {
		return repository.findById(id);
	}

	@Override
	public boolean isKeyExist(String id) {
		Counter doc = findByCounterId(id);
		if (doc == null)
			return false;
		return true;
	}

	@Override
	public void initNewDoc(String id) {
		repository.save(new Counter(id));
	}

}
