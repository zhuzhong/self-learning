/**
 * 
 */
package com.zz.learning.mongodb;

import java.net.UnknownHostException;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * @author Administrator
 *
 */
public class TimeTest {

	DB db;
	DBCollection coll;

	@Before
	public void init() throws UnknownHostException {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		db = mongoClient.getDB("test");

		coll = db.getCollection("tester");

	}

	@Test
	public void incTest() {
		coll.insert(new BasicDBObject().append("x", 1));

		DBObject dbo = coll.findOne();
		ObjectId id = (ObjectId) dbo.get("_id");
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			coll.update(
					new BasicDBObject().append("_id", id),
					new BasicDBObject().append("$inc",
							new BasicDBObject().append("x", 1)));

		}
		System.out.println("elapsedd time is "
				+ (System.currentTimeMillis() - start) + " ms");
	}
}
