package com.chotoxautinh.dao.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.chotoxautinh.dao.CounterDao;
import com.chotoxautinh.service.Counter;
import com.chotoxautinh.service.CounterException;

@Component
public class CounterDaoImpl implements CounterDao{
	@Autowired
	private MongoTemplate mongoTemplate;

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

}
