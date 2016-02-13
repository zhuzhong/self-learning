/**
 * 
 */
package com.pc.disruptor.ex2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.IgnoreExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

/**
 * @author sunff
 *
 */
public class ListConsumer {

	
	private RingBuffer<ListEvent> ringBuffer;
	public void consume() {
		ExecutorService service=Executors.newFixedThreadPool(2);
		Disruptor<ListEvent> disruptor = new Disruptor<ListEvent>(
				new EventFactory<ListEvent>() {

					@Override
					public ListEvent newInstance() {
						
						return new ListEvent();
					}
				}, 1024, service, ProducerType.SINGLE,
				new YieldingWaitStrategy());
		disruptor.handleExceptionsWith(new IgnoreExceptionHandler());
		disruptor.handleEventsWithWorkerPool(new ListEventWorkHandler("T1"),new ListEventWorkHandler("T2"));
		ringBuffer=disruptor.start();
	}
	public RingBuffer<ListEvent> getRingBuffer() {
		return ringBuffer;
	}
	
	
}
