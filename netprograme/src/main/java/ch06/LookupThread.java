package ch06;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

public class LookupThread extends Thread {

	private List<String> entries;
	private PooledWeblog log; // used for callbacks

	public LookupThread(List<String> entries, PooledWeblog log) {
		this.entries = entries;
		this.log = log;
	}

	public void run() {
		String entry;
		while (true) {

			synchronized (entries) {
				while (entries.size() == 0) {
					if (log.isFinished()) {
						return;
					}

					try {
						entries.wait();
					} catch (InterruptedException ex) {
					}
				}
				entry = entries.remove(entries.size() - 1);
			
			}

			int index = entry.indexOf(' ', 0);
			String remoteHost = entry.substring(0, index);
			String theRest = entry.substring(index, entry.length());

			try {
				remoteHost = InetAddress.getByName(remoteHost).getHostName();
			} catch (Exception ex) {
				// remoteHost remains in dotted quad format
			}

			try {
				log.log(remoteHost + theRest);
			} catch (IOException ex) {
			}
			//this.yield();
			Thread.yield();

		}

	}

}
