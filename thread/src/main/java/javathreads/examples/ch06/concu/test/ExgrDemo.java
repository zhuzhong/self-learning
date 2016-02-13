package javathreads.examples.ch06.concu.test;

import java.util.concurrent.Exchanger;

public class ExgrDemo {
	public static void main(String args[]) {
		Exchanger<String> exgr = new Exchanger<String>();
		new UseString(exgr);
		new MakeString(exgr);
	}
}

class MakeString implements Runnable {
	Exchanger<String> ex;

	MakeString(Exchanger<String> c) {
		ex = c;
		new Thread(this).start();
	}

	public void run() {

		try {
			for (int i = 0; i < 10; i++) {
				String str = Thread.currentThread().getName() + " time is " + i;
				str = ex.exchange(str);
				System.out.println(str);
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

class UseString implements Runnable {
	Exchanger<String> ex;

	String str;

	UseString(Exchanger<String> c) {
		ex = c;
		new Thread(this).start();
	}

	public void run() {

		for (int i = 0; i < 10; i++) {
			try {
				str = Thread.currentThread().getName();
				str = ex.exchange(new String(str + "  oook"));
				System.out.println("Got: " + str);
			} catch (InterruptedException exc) {
				System.out.println(exc);
			}
		}
	}
}
