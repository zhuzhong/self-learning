/**
 * 
 */
package com.zz.learning.mongodb;

import java.net.UnknownHostException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryOperators;

/**
 * @author Administrator
 *
 */
public class FindTest {

	DB db;
	DBCollection coll;

	@Before
	public void init() throws UnknownHostException {
		MongoClient mongoClient = new MongoClient("192.168.2.101", 27017);
		db = mongoClient.getDB("test");

		coll = db.getCollection("users");

	}

	
	@Test
	public void aggregate(){
		List<DBObject> ps=new ArrayList<DBObject>();
		ps.add(new BasicDBObject().append("$project", new BasicDBObject().append("author", 1)));
		ps.add(new BasicDBObject().append("$group", new BasicDBObject().append("_id", "$author").append("count", new BasicDBObject().append("$sum", 1))));
		ps.add(new BasicDBObject().append("$sort", new BasicDBObject().append("count", -1)));
		ps.add(new BasicDBObject().append("$limit", 5));
		coll = db.getCollection("articles");
		AggregationOutput ao=coll.aggregate(ps);
		for(DBObject o:ao.results()){
			System.out.println(o);
		}
		
	}
	@Test
	public void collectionNames() {
		Set<String> ss = db.getCollectionNames();
		for (String s : ss) {
			System.out.println(s);
		}
	}

	@Test
	public void elemMatch() {
		/*
		 * StringBuilder sb=new StringBuilder();
		 * sb.append("\"author\":\"joe\""); sb.append(",");
		 * sb.append("\"score\":{\"$gte\":5}");
		 */

		DBObject re = coll.find(
				new BasicDBObject().append("comments", new BasicDBObject()
						.append("$elemMatch",
								new BasicDBObject().append("author", "joe")
										.append("score",
												new BasicDBObject().append(
														"$gte", 5))
						// new String(sb.toString())
						))).explain();

		System.out.println(re);
	}

	@Test
	public void pageFind() {
		// 分页查询
		int pageNo = 10;
		int pageSize = 25;
		DBObject re = coll.find(new BasicDBObject())
				.sort(new BasicDBObject().append("x", 1)).limit(pageSize)
				.skip(pageNo * pageSize).explain();
		System.out.println(re);
	}

	@Test
	public void modTest() {

		DBObject re = coll.find(
				new BasicDBObject().append("id_num", new BasicDBObject()
						.append(QueryOperators.MOD, new Object[] { 5, 1 })))
				.explain();
		System.out.println(re);
	}

	@Test
	public void inAndOrTest() {

		BasicDBList inList = new BasicDBList();
		inList.add(725);
		inList.add(542);
		inList.add(390);

		BasicDBList orList = new BasicDBList();
		orList.add(new BasicDBObject().append("ticket_no",
				new BasicDBObject().append(QueryOperators.IN, inList)));
		orList.add(new BasicDBObject().append("winner", true));
		DBObject re = coll.find(
				new BasicDBObject().append(QueryOperators.OR, orList))
				.explain();
		System.out.println(re);
	}

	@Test
	public void explainTest() {
		BasicDBList list = new BasicDBList();
		list.add(new BasicDBObject().append("ticket_no", 725));
		list.add(new BasicDBObject().append("winner", true));
		DBObject re = coll.find(
				new BasicDBObject().append(QueryOperators.OR, list)).explain();
		System.out.println(re);

	}

	public void commonFind() {
		coll.find();
	}

	public void andFind() {
		coll.find(new BasicDBObject().append("name", "test").append("age", 25));

	}

	public void getFind() {
		coll.find(new BasicDBObject().append(
				"age",
				new BasicDBObject().append(QueryOperators.GTE, 18).append(
						QueryOperators.LTE, 80)));
	}

	public void dateFind() {
		coll.find(new BasicDBObject().append("registered", new BasicDBObject()
				.append(QueryOperators.LT, new Date(2007, 0, 1))));

		coll.find(new BasicDBObject().append("username",
				new BasicDBObject().append(QueryOperators.NE, "joe")));

		coll.find(new BasicDBObject().append(
				"ticket_no",
				new BasicDBObject().append(QueryOperators.IN, new Object[] {
						725, 542, 390 })));

		coll.find(new BasicDBObject().append(
				"ticket_no",
				new BasicDBObject().append(QueryOperators.NIN, new Object[] {
						725, 542, 390 })));

	}
}
