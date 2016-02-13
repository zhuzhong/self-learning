
package com.ronsoft.books.nio.channels;

import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.channels.Pipe;
import java.nio.channels.Channels;
import java.util.Random;

/**
 * Test Pipe objects using a worker thread.
 *
 * Created April, 2002
 * @author Ron Hitchens (ron@ronsoft.com)
 * @version $Id: PipeTest.java,v 1.2 2002/05/19 04:55:45 ron Exp $
 */
public class PipeTest
{
	public static void main (String [] argv)
		throws Exception
	{
		
		ByteBuffer buffer = ByteBuffer.allocate (100);
		WritableByteChannel out = Channels.newChannel (System.out);
		for (int i=0;i<10;i++){
			String a="No good deed goes unpunished "+i;
			buffer.put(a.getBytes("UTF-8"));
			buffer.put("\r\n".getBytes("UTF-8"));
			buffer.flip();
			out.write(buffer);
			buffer.clear();
		}
		writeData();
	}

	
	private static void writeData() throws Exception
	{
		// start worker and get read end of channel
				ReadableByteChannel workerChannel = startWorker (10);
				
				ByteBuffer buffer = ByteBuffer.allocate (100);
				// wrap a channel around stdout
				WritableByteChannel out = Channels.newChannel (System.out);
				while (workerChannel.read (buffer) >= 0) {
					buffer.flip();
					out.write (buffer);
					buffer.clear();
				}
	}
	// This method could as easily return a SocketChannel or
	// FileChannel instance.
	private static ReadableByteChannel startWorker (int reps)
		throws Exception
	{
		Pipe pipe = Pipe.open();
		Worker worker = new Worker (pipe.sink(), reps);

		worker.start();

		return (pipe.source());
	}

	// -----------------------------------------------------------------

	/**
	 * A worker thread object which writes data down a channel.
	 * Note: this object knows nothing about Pipe, uses only a
	 * generic WritableByteChannel.
	 */
	private static class Worker extends Thread
	{
		WritableByteChannel channel;
		private int reps;

		Worker (WritableByteChannel channel, int reps)
		{
			this.channel = channel;
			this.reps = reps;
		}

		// thread execution begins here
		public void run()
		{
			ByteBuffer buffer = ByteBuffer.allocate (100);

			try {
				for (int i = 0; i < this.reps; i++) {
					doSomeWork (buffer);

					// channel may not take it all at once
					while (channel.write (buffer) > 0) {
						// empty
					}
				}

				this.channel.close();
			} catch (Exception e) {
				// easy way out, this is demo code
				e.printStackTrace();
			}
		}

		private String [] products = {
			"No good deed goes unpunished",
			"To be, or what?",
			"No matter where you go, there you are",
			"Just say \"Yo\"",
			"My karma ran over my dogma"
		};

		private Random rand = new Random();

		private void doSomeWork (ByteBuffer buffer)
			throws Exception
		{
			int product = rand.nextInt (products.length);

			buffer.clear();
			String a=Thread.currentThread().getName()+" "+products [product];
			buffer.put (a.getBytes ("US-ASCII"));
			//buffer.put (products [product].getBytes ("US-ASCII"));
			buffer.put ("\r\n".getBytes ("US-ASCII"));
			buffer.flip();
		}
	}
}
