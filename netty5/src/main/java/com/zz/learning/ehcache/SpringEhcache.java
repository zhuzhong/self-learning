/**
 * 
 */
package com.zz.learning.ehcache;

import java.util.ArrayList;
import java.util.List;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author sunff
 *  spring集成ehcache测试
 */
public class SpringEhcache {

	
	public static void main(String args[]){
		ClassPathXmlApplicationContext c=new ClassPathXmlApplicationContext("com/zz/learning/ehcache/spring-ehcache.xml");
		Ehcache cache=c.getBean("ehCache",Ehcache.class);
		List<User> users=new ArrayList<User>();
		for(int i=0;i<10;i++){
			User u=new User();
			u.setPass("pass"+i);
			u.setUserName("zhuzhong"+i);
			users.add(u);
		}
		for(int j=0;j<10;j++){
			Element e=new Element(j, users);
			cache.put(e);
		}
		
		Element j4=cache.get(4);
		System.out.println(j4.getKey());
		System.out.println(j4.getObjectKey());
		
		List<User> j4Users=(List<User>) j4.getObjectValue();
		for(User u:j4Users){
			System.out.println(u);
		}
	}
}
