/**
 * 
 */
package com.zz.learning.mongodb;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceOutput;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

/**
 * @author Administrator
 *
 */
public class MapReduceTest {

	DB db;
	DBCollection coll;

	@Before
	public void init() throws UnknownHostException {
		MongoClient mongoClient = new MongoClient("192.168.2.101", 27017);
		db = mongoClient.getDB("test");

		coll = db.getCollection("users");

	}

	@Test
	public void insert() {
		List<DBObject> ls = new ArrayList<DBObject>();

		for (int i = 0; i < 1000; i++) {
			double rid = Math.floor(Math.random() * 10);
			double p = Math.floor(Math.random() * 10);
			if (rid < 4D) {
				ls.add(new BasicDBObject().append("user", "Joe")
						.append("sku", rid).append("price", p));
			} else if (rid >= 4D && rid < 7D) {
				ls.add(new BasicDBObject().append("user", "Josh")
						.append("sku", rid).append("price", p));
			} else {

				ls.add(new BasicDBObject().append("user", "Ken")
						.append("sku", rid).append("price", p));
			}

		}
		
		coll.insert(ls);
	}
	
	

	
	
	@Test
	public void mapReduce() throws UnknownHostException{
        /***
         *  book1 = {name : "Understanding JAVA", pages : 100}
         *  book2 = {name : "Understanding JSON", pages : 200}
         *  db.books.save(book1)
         *  db.books.save(book2)
         *  book = {name : "Understanding XML", pages : 300}
         *  db.books.save(book)
         *  book = {name : "Understanding Web Services", pages : 400}
         *  db.books.save(book)
         *  book = {name : "Understanding Axis2", pages : 150}
         *  db.books.save(book)  
         *  
        var map = function() {
            var category;
            if ( this.pages >= 250 )
                category = 'Big Books';
            else
                category = "Small Books";
            emit(category, {name: this.name});
        };
        var reduce = function(key, values) {
            var sum = 0;
            values.forEach(function(doc) {
                sum += 1;
            });
            return {books: sum};
        };       
        var count  = db.books.mapReduce(map, reduce, {out: "book_results"});
         */
        try {

            DBCollection books = db.getCollection("books");

            BasicDBObject book = new BasicDBObject();
            book.put("name", "Understanding JAVA");
            book.put("pages", 100);
            books.insert(book);
            
            book = new BasicDBObject();  
            book.put("name", "Understanding JSON");
            book.put("pages", 200);
            books.insert(book);
            
            book = new BasicDBObject();
            book.put("name", "Understanding XML");
            book.put("pages", 300);
            books.insert(book);
            
            book = new BasicDBObject();
            book.put("name", "Understanding Web Services");
            book.put("pages", 400);
            books.insert(book);
          
            book = new BasicDBObject();
            book.put("name", "Understanding Axis2");
            book.put("pages", 150);
            books.insert(book);
            
            String map = "function() { "+ 
                      "var category; " +  
                      "if ( this.pages >= 250 ) "+  
                      "category = 'Big Books'; " +
                      "else " +
                      "category = 'Small Books'; "+  
                      "emit(category, {name: this.name});}";
            
            String reduce = "function(key, values) { " +
                                     "var sum = 0; " +
                                     "values.forEach(function(doc) { " +
                                     "sum += 1; "+
                                     "}); " +
                                     "return {books: sum};} ";
            
            MapReduceCommand cmd = new MapReduceCommand(books, map, reduce,
              null, MapReduceCommand.OutputType.INLINE, null);

            MapReduceOutput out = books.mapReduce(cmd);
      
            for (DBObject o : out.results()) {
             System.out.println(o.toString());
            }
           } catch (Exception e) {
             e.printStackTrace();
           }
	}
	
}
