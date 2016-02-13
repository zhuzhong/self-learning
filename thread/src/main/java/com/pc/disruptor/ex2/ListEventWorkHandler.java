/**
 * 
 */
package com.pc.disruptor.ex2;

import java.util.List;

import com.lmax.disruptor.WorkHandler;

/**
 * @author sunff
 *
 */
public class ListEventWorkHandler  implements WorkHandler<ListEvent>{

	
	private String handleName;
	public ListEventWorkHandler(String name){
		this.handleName=name;
	}
	@Override
	public void onEvent(ListEvent event) throws Exception {
		List<User> users=event.getUsers();
		for(User u:users){
			System.out.println("handleName is "+handleName+ u.toString());
		}
		
	}

}
