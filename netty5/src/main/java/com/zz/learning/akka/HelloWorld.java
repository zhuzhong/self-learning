/**
 * 
 */
package com.zz.learning.akka;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * @author sunff
 *
 */
public class HelloWorld extends UntypedActor {

	private ActorRef greeter;

	@Override
	public void preStart() {
		greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");
		System.out.println("Greeter Actor path:" + greeter.path());
		greeter.tell(Msg.GREET, getSelf());
	}

	@Override
	public void onReceive(Object msg) throws Exception {
		if (msg == Msg.DONE) {
			greeter.tell(Msg.GREET, getSelf());
			getContext().stop(getSelf());
		} else {
			unhandled(msg);
		}
	}

}
