/**
 * 
 */
package com.zz.learning.akka;

import akka.actor.UntypedActor;

/**
 * @author sunff
 *
 */
public class Greeter extends UntypedActor {

	@Override
	public void onReceive(Object msg) throws Exception {
		if (msg == Msg.GREET) {
			System.out.println("Hello World!");
			getSender().tell(Msg.DONE, getSelf());
		} else {
			unhandled(msg);
		}
	}

}
