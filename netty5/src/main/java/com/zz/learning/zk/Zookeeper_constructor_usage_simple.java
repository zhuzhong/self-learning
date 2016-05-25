/**
 * 
 */
package com.zz.learning.zk;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author sunff
 *
 */
public class Zookeeper_constructor_usage_simple implements Watcher {

	private static CountDownLatch latch = new CountDownLatch(1);

	public static void main(String[] args) throws IOException {
		System.out.println("start...");
		ZooKeeper zk = new ZooKeeper("localhost:2181", 5000,
				new Zookeeper_constructor_usage_simple());
		System.out.println(zk.getState());
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void process(WatchedEvent arg0) {
		System.out.println("receive watch event" + arg0);
		if (KeeperState.SyncConnected == arg0.getState()) {
			latch.countDown();
			System.out.println("end...");
		}

	}

}
