package com.webserver.lf;

import java.util.ArrayList;
import java.util.List;

public class LfTcpServer2 {
	private final static Object monitor = new Object();
	private final static int THREAD_POO_SIZE = 5;
/**
 * 它的功能应当是能够满足事件之间的同步的类似队列的功能，当然也不需要是队列，比如观察者模式就可以满足.
 */
	private Reactor handleSet = new Reactor();  

	public LfTcpServer2(Reactor handleSet) {
		this.handleSet = handleSet;
	}
	public void addHandles(Handle h){
		handleSet.addHandles(h);
	}
	public void init() {
		for (int i = 0; i < THREAD_POO_SIZE; i++) {
			new WorkThread(this).start();
		}
		this.promoteNewLeader();
	}

	public void join() {
		for (;;) {
			waitToLeader();
			// select, blocking
			Handle handle = handleSet.select();

			// promote new leader
			promoteNewLeader();

			// process handl
			handleSet.handle(handle);

			// reenter to next loop
		}
	}

	private void waitToLeader() {
		synchronized (monitor) {
			try {
				monitor.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void promoteNewLeader() {
		synchronized (monitor) {
			monitor.notify();
			//monitor.notifyAll();
			
		}
	}

	class WorkThread extends Thread {
		LfTcpServer2 tp;

		public WorkThread(LfTcpServer2 tp) {
			this.tp = tp;
		}

		@Override
		public void run() {
			tp.join();
		}

	}

	private static class Reactor {

		public Handle select() {
			return null;
		}

		public void handle(Handle handle) {

		}

		
		private List<Handle> handles=null;
		public void addHandles(Handle h){
			if(handles==null){
				handles=new ArrayList<Handle>();
			}
			handles.add(h);
		}
	}

	private static class Handle {

	}
}
