/**
 * 
 */
package com.pc.disruptor.ex2;

import java.util.ArrayList;
import java.util.List;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

/**
 * @author sunff
 *
 */
public class ListProductor {

	
	private RingBuffer<ListEvent> ringBuffer;

	public ListProductor(RingBuffer<ListEvent> ringBuffer) {
		super();
		this.ringBuffer = ringBuffer;
	}
	
	public void product(){
		List<User> users=new ArrayList<User>();
		for(int i=0;i<1000;i++){
		
			User u=new User();
			u.setUserName("laopo"+i);
			u.setPassword("fengfang"+i);
			users.add(u);
			if(i%10==0&i>1){
				product(users);
				users=new ArrayList<User>();
			}
		}
	}
	public void product(List<User> users){
		ringBuffer.publishEvent(new EventTranslatorOneArg<ListEvent,List<User>>() {

			@Override
			public void translateTo(ListEvent event, long arg1, List<User> arg2) {
				event.setUsers(arg2);
			}			
		}, users);
	}
	
	
}
