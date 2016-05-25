/**
 * 
 */
package com.zz.learning.zk;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;

/**
 * @author sunff
 *
 */
public class CreateApiSyncUsage implements Watcher {

	private static CountDownLatch latch = new CountDownLatch(1);

	public static void main(String[] args) throws Exception {
		System.out.println("start...");
		ZooKeeper zk = new ZooKeeper("localhost:2181", 5000,
				new CreateApiSyncUsage());
		
		System.out.println(zk.getState());
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String path1 = zk.create("/zk-test-ephemeral", "data".getBytes(),
				Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		System.out.println("success create znode" + path1);

		path1 = zk.create("/zk-test-ephemeral", "data".getBytes(),
				Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		System.out.println("success create znode" + path1);
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
