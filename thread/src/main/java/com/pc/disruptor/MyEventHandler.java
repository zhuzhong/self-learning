package com.pc.disruptor;

import com.lmax.disruptor.EventHandler;
/**
 * 消费事件的地方，即如何消费
 * @author sunff
 *
 */
public class MyEventHandler implements EventHandler<MyEvent> {
	@Override
	public void onEvent(MyEvent event, long sequence, boolean endOfBatch)
			throws Exception {
		System.out.println(event);
	}
}