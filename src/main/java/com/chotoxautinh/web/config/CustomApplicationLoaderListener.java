package com.chotoxautinh.web.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.chotoxautinh.dao.CounterDao;

/**
 * @author chotoxautinh
 *
 * http://chotoxautinh.com/
 * Mar 23, 2016
 */
@Component
public class CustomApplicationLoaderListener implements ApplicationListener<ContextRefreshedEvent> {

	static Logger log = LoggerFactory.getLogger(CustomApplicationLoaderListener.class);

	@Autowired
	private CounterDao counterDao;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		log.info("\n\n\tSpring MVC Application Inited\n");
		initCounterCollections();
	}

	private void initCounterCollections() {
		insertCounterDocIfNotExist("emails");
	}

	private void insertCounterDocIfNotExist(String key) {
		if (counterDao.isKeyExist(key))
			return;
		counterDao.initNewDoc(key);
		log.info("Created new counter document with _id: " + key + " \n");
	}

}
