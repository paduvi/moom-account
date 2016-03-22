package com.chotoxautinh.dao;

public interface CounterDao {
	int getNextSequence(String collectionName);
	
	void createNewDoc(String id);

	boolean isKeyExist(String id);
}
