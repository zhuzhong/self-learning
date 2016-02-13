package com.pc.disruptor;

import com.lmax.disruptor.*;

/**
 * 生产者
 * 
 * @author sunff
 *
 */
public class MyEventProducer {

	private RingBuffer<MyEvent> ringBuffer;

	public MyEventProducer(RingBuffer<MyEvent> ringBuffer) {
		this.ringBuffer = ringBuffer;
	}

	private static final EventTranslatorOneArg<MyEvent, Long> TRANSLATOR = new EventTranslatorOneArg<MyEvent, Long>() {
		@Override
		public void translateTo(MyEvent event, long sequence, Long value) {
			event.setValue(value);
		}
	};

	public void onData(final Long value) {
		ringBuffer.publishEvent(TRANSLATOR, value);
	}
}