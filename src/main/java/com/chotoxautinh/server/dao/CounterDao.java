/**
 * @author chotoxautinh
 *
 * Mar 23, 2016 - http://chotoxautinh.com/
 */
package com.chotoxautinh.server.dao;

public interface CounterDao {
	/**
	 * Generate auto-increment index for a specific collection
	 * 
	 * @param collectionName
	 *            - String
	 * @return return an integer value of increased index
	 */
	int getNextSequence(String collectionName);

	/**
	 * Initialize a new counter document for a specific collection
	 * 
	 * @param id
	 *            - String represent for the collection name
	 */
	void initNewDoc(String id);

	/**
	 * Check for counter document represent for a specific collection has
	 * existed or not
	 * 
	 * @param id
	 *            - String represent for the collection name
	 * @return
	 */
	boolean isKeyExist(String id);
}
