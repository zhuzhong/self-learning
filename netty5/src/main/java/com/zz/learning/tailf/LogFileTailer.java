package com.zz.learning.tailf;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class LogFileTailer extends Thread {

	private long sampleInterval = 9000;

	private File logfile;

	private boolean startAtBeginning = true;

	private volatile boolean tailing = true;

	private Set<LogFileTailerListener> listeners = new HashSet<LogFileTailerListener>();

	public LogFileTailer(File file) {
		this.logfile = file;
	}

	public LogFileTailer(File file, long sampleInterval,
			boolean startAtBeginning) {
		this.logfile = file;
		this.sampleInterval = sampleInterval;
		this.startAtBeginning = startAtBeginning;
	}

	public void addLogFileTailerListener(LogFileTailerListener l) {
		this.listeners.add(l);
	}

	public void removeLogFileTailerListener(LogFileTailerListener l) {
		this.listeners.remove(l);
	}

	protected void fireNewLogFileLine(String line) {
		for (Iterator<LogFileTailerListener> i = this.listeners.iterator(); i
				.hasNext();) {
			LogFileTailerListener l = i.next();
			l.newLogFileLine(line);
		}
	}

	public void stopTailing() {
		this.tailing = false;
	}

	public void run() {
		long filePointer = 0;

		if (this.startAtBeginning) {
			filePointer = 0;
		} else {
			filePointer = this.logfile.length();
		}

		try {
			RandomAccessFile file = new RandomAccessFile(logfile, "r");
			while (this.tailing) {
				long fileLength = this.logfile.length();
				if (fileLength < filePointer) {
					file = new RandomAccessFile(logfile, "r");
					filePointer = 0;
				}
				if (fileLength > filePointer) {
					file.seek(filePointer);
					String line = file.readLine();
					while (line != null) {
						this.fireNewLogFileLine(line);
						line = file.readLine();
					}
					filePointer = file.getFilePointer();
				}
				sleep(this.sampleInterval);
			}
			if (file != null)
				file.close();
		} catch (IOException e) {

		} catch (InterruptedException e) {

		}
	}

	/*
	 * public void setTailing(boolean tailing) { this.tailing = tailing; }
	 */

}