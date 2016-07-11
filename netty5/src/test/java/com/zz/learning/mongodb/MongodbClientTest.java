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
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

/**
 * @author Administrator
 *
 */
public class MongodbClientTest {

	DB db;
	DBCollection coll;

	@Before
	public void init() throws UnknownHostException {
		MongoClient mongoClient = new MongoClient("192.168.2.101", 27017);
		db = mongoClient.getDB("test");
		coll = db.getCollection("testCollection");
	}

	// 增
	@Test
	public void insertAndFindTest() throws UnknownHostException {

		BasicDBObject doc = new BasicDBObject("name", "MongoDB")
				.append("type", "database").append("count", 1)
				.append("info", new BasicDBObject("x", 203).append("y", 102));

		coll.insert(doc);

		DBObject myDoc = coll.findOne();
		System.out.println(myDoc);
	}

	@Test
	public void inc() throws UnknownHostException {
		BasicDBObject query = new BasicDBObject("name", "MongoDB");

		coll.update(
				query,
				new BasicDBObject().append("$inc",
						new BasicDBObject().append("count", -50)));

	}

	@Test
	public void each() throws UnknownHostException {
		BasicDBObject query = new BasicDBObject("name", "MongoDB");

		coll.update(query, new BasicDBObject().append(
				"$addToSet",
				new BasicDBObject().append(
						"emails",
						new BasicDBObject().append("$each", new Object[] {
								"joe@php2.net", "joe@example.com" }))));

	}

	@Test
	public void pull() throws UnknownHostException {
		BasicDBObject query = new BasicDBObject("name", "MongoDB");
		coll.update(query, new BasicDBObject().append("$pull",
				new BasicDBObject().append("emails", "joe%example.com")));
	}

	// 查
	@Test
	public void select() throws UnknownHostException {
		/*
		 * BasicDBObject doc = new BasicDBObject("name", "MongoDB")
		 * .append("type", "database").append("count", 1) .append("info", new
		 * BasicDBObject("x", 203).append("y", 102));
		 * 
		 * coll.insert(doc);
		 */

		DBObject myDoc = coll.findOne();
		for (String key : myDoc.keySet()) {
			System.out.println("key=" + key);
		}
		String _id = ((ObjectId) myDoc.get("_id")).toHexString();
		System.out.println(_id);
		System.out.println(myDoc);
	}

	// QueryOperators
	// 更新
	@Test
	public void update() throws UnknownHostException {
		BasicDBObject doc = new BasicDBObject("name", "MongoDB")
		/*
		 * .append("type", "database").append("count", 1) .append("info", new
		 * BasicDBObject("x", 203).append("y", 102))
		 */
		;

		/* coll.insert(doc); */

		DBObject dd = new BasicDBObject().append("$set",
				new BasicDBObject().append("count", 100));
		coll.update(doc, dd);
		DBObject myDoc = coll.findOne(doc);
		System.out.println(myDoc);

	}

	// 删
	@Test
	public void remove() throws UnknownHostException {
		BasicDBObject doc = new BasicDBObject("name", "MongoDB");
		coll.remove(doc);
	}

	@Test
	public void insertWithID() throws UnknownHostException {
		try {
			// Get collection from MongoDB, database named "yourDB"
			// 从Mongodb中获得名为yourColleection的数据集合，如果该数据集合不存在，Mongodb会为其新建立
			DBCollection collection = db.getCollection("things");
			// 使用BasicDBObject对象创建一个mongodb的document,并给予赋值。
			BasicDBObject document = new BasicDBObject();
			document.put("id", 1001);
			document.put("msg", "hello world mongoDB in Java");
			// 将新建立的document保存到collection中去
			collection.insert(document);

			// 创建要查询的document
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("id", 1001);
			// 使用collection的find方法查找document
			DBCursor cursor = collection.find(searchQuery);
			// 循环输出结果
			while (cursor.hasNext()) {
				DBObject result = cursor.next();
				System.out.println(result);
			}
			System.out.println("Done");

		} catch (MongoException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void save() throws UnknownHostException {

		DBCollection coll = db.getCollection("c");
		BasicDBObject doc = new BasicDBObject("name", "MongoDB")
				.append("type", "database").append("count", 1)
				.append("info", new BasicDBObject("x", 203).append("y", 102));
		for (int i = 0; i < 10; i++) {
			doc = new BasicDBObject("name", "MongoDB")
					.append("type", "database").append("count", 1)
					.append("info", new BasicDBObject("x", 203).append("y", i));
			coll.save(doc);
		}
		System.out.println(doc);
	}

	@Test
	public void blogSave() throws UnknownHostException {

		DBCollection coll = db.getCollection("blog");
		coll.drop();
		BasicDBObject doc = new BasicDBObject("content", "MongoDB").append(
				"comments", new BasicDBObject[] {
						new BasicDBObject("author", "joe").append("score", 3)
								.append("comment", "nice post"),
						new BasicDBObject("author", "mary").append("score", 6)
								.append("comment", "terrible post"),
						new BasicDBObject("author", "joe").append("score", 6)
								.append("comment", "terrible post333") })

		;
		coll.save(doc);
		System.out.println(doc);
	}

}