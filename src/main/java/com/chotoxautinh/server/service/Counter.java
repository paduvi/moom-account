package com.chotoxautinh.server.service;

import javax.persistence.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author chotoxautinh
 *
 * http://chotoxautinh.com/
 * Mar 23, 2016
 */
@Entity
@Document(indexName = "counters", type = "article")
public class Counter {
	@Id
	private String id;

	private int seq;

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public Counter() {
		// TODO Auto-generated constructor stub
	}
	
	public Counter(String id){
		this.id = id;
		this.seq = 0;
	}
}
