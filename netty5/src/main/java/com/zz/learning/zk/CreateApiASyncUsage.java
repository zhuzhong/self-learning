/**
 * 
 */
package com.zz.learning.zk;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;

/**
 * @author sunff
 *
 */
public class CreateApiASyncUsage implements Watcher {

	private static CountDownLatch latch = new CountDownLatch(1);

	public static void main(String[] args) throws Exception {
		System.out.println("start...");
		ZooKeeper zk = new ZooKeeper("localhost:2181", 5000,
				new CreateApiASyncUsage());

		System.out.println(zk.getState());
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		zk.create("/zk-test-ephemeral", "data".getBytes(), Ids.OPEN_ACL_UNSAFE,
				CreateMode.EPHEMERAL, new StringCallback(), "oook");
		zk.create("/zk-test-ephemeral", "data".getBytes(), Ids.OPEN_ACL_UNSAFE,
				CreateMode.EPHEMERAL, new StringCallback(), "oook");

		zk.create("/zk-test-ephemeral", "data".getBytes(), Ids.OPEN_ACL_UNSAFE,
				CreateMode.EPHEMERAL_SEQUENTIAL, new StringCallback(), "oook");
		TimeUnit.SECONDS.sleep(100000L);
	}

	@Override
	public void process(WatchedEvent arg0) {
		System.out.println("receive watch event" + arg0);
		if (KeeperState.SyncConnected == arg0.getState()) {
			latch.countDown();
			System.out.println("end...");
		}

	}

	private static class StringCallback implements AsyncCallback.StringCallback {

		@Override
		public void processResult(int rc, String path, Object ctx, String name) {
			System.out.println("result:" + rc + ":" + path + ":" + ctx + ":"
					+ name);

		}

	}

}
