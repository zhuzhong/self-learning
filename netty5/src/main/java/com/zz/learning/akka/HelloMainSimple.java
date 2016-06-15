/**
 * 
 */
package com.zz.learning.akka;

import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * @author sunff
 *
 */
public class HelloMainSimple {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String path = "com/zz/learning/akka/samplehello.conf";
		path="samplehello.conf";
		ActorSystem system = ActorSystem.create("hello",
				ConfigFactory.load(path));
		ActorRef a = system.actorOf(Props.create(HelloWorld.class),
				"helloWorld");
		System.out.println("helloworld actor path:" + a.path());

	}

}
