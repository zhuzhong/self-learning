package com.pc.disruptor;

/**
 * 事件，被生产者创建的事件，被消费者消费的事件
 * 
 * @author sunff
 *
 */
public class MyEvent {

	private long value;

	public void setValue(long value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "MyEvent{" + "value=" + value + '}';
	}
}