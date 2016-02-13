package com.pc.ex2;

public abstract class PeriodicalTask extends Thread {
	protected abstract void doWork() throws Exception;

	public PeriodicalTask() {

	}

	public PeriodicalTask(int period) {

	}
   @Override
	public void run() {
		try {
			doWork();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void pause() {
		try {
			super.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	public void shutdown() {
		super.interrupt();
	}
}
