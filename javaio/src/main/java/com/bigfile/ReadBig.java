package com.bigfile;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;



public class ReadBig {
  //C:\Users\snoopy\Desktop\work\20141028
	static String filePath = "C:/Users/snoopy/Desktop/work/20141028/124/catalina.out.2014-09-04";

	//static String filePath = "C:/Users/snoopy/Desktop/20141028/124/flight_date.sql";
	
	/*
	 * 这个方法会出现卡顿,但是一分钟之内读出了文件,spring batch 就是按照这个读的,
	 * 对于大的文件有时会出现内存溢出
	 */
	static void largeFileIO(String inputFile, String outputFile) {
		long beginTime=System.currentTimeMillis();
		System.out.println("begintime"+beginTime);
		try {
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(new File(inputFile)));
			BufferedReader in = new BufferedReader(new InputStreamReader(bis,
					"utf-8"), 5 * 1024 * 1024);// 10M缓存
			
			//BufferedReader in=new BufferedReader(new InputStreamReader(new FileInputStream(inputFile),"utf-8"));
			
			// FileWriter fw = new FileWriter(outputFile);
			while (in.ready()) {
				String line = in.readLine();
				//System.out.println(line);
				// fw.append(line + " ");
			}
			in.close();
			// fw.flush();
			// fw.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		System.out.println("largerFileIO time="+((long)System.currentTimeMillis()-beginTime));
	}

	/**
	 * 在规定的时间内读不出文件
	 * 
	 * @throws IOException
	 */
	static void largerFileIO2() throws IOException {
		long beginTime=System.currentTimeMillis();
		System.out.println("begintime"+beginTime);
		String path = filePath;
		RandomAccessFile br = new RandomAccessFile(path, "r");// 这里rw看你了。要是之都就只写r
		String str = null;
		//app = null;
		//int i = 0;
		while ((str = br.readLine()) != null) {
			// i++;
			// app = app + str;
			// if (i >= 100) {// 假设读取100行
			// i = 0;
			// // 这里你先对这100行操作，然后继续读
			// app = null;
			// }
			//System.out.println(new String(str.getBytes("iso8859-1"), "gbk"));
		}
		br.close();
		System.out.println("largerFileIO2 time="+((long)System.currentTimeMillis()-beginTime));
	}

	/**
	 * 这个也不行，也会出现卡顿
	 * begintime1415249146701
       largerFileIO3 time=33137  1024byte

      begintime1415249224567
      largerFileIO3 time=39503  5m
	 * @param path
	 * @throws Exception
	 */
	static void largerFileIO3(String path) throws Exception {
		long beginTime=System.currentTimeMillis();
		System.out.println("begintime"+beginTime);
		int bufSize = 1024;
		byte[] bs = new byte[bufSize];
		ByteBuffer byteBuf = ByteBuffer.allocate(bufSize);
		FileChannel channel = new RandomAccessFile(path, "r").getChannel();
		while (channel.read(byteBuf) != -1) {
			int size = byteBuf.position();
			byteBuf.rewind();
			byteBuf.get(bs); // 把文件当字符串处理，直接打印做为一个例子。
			//System.out.print(new String(bs, 0, size));
			byteBuf.clear();
		}

		System.out.println("largerFileIO3 time="+((long)System.currentTimeMillis()-beginTime));
	}

	

	/**
	 * begintime1415249390361
     * largerFileIO5 time=26297
	 * 会卡
	 * 
	 * @param path
	 * @throws Exception
	 */
	static void largerFileIO5(String path) throws Exception {
		long beginTime=System.currentTimeMillis();
		System.out.println("begintime"+beginTime);
		
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line = null;
		StringBuilder sb=new StringBuilder();
		while ((line = br.readLine()) != null) {
			
//			if(line.indexOf(";")>0){
//				sb.append(line);
//				//处理sb
//				System.out.println(sb.toString());
//				sb.delete(0, sb.capacity());
//			}else{
//				sb.append(line);
//			}
		
			System.out.println(line);
		}
		System.out.println("largerFileIO5 time="+((long)System.currentTimeMillis()-beginTime));


	}
	
	/**
	 * map太大，不行，读不出
	 * 
	 * @param path
	 * @throws Exception
	 */
	static void largerFileIO4(String path) throws Exception {

		final int BUFFER_SIZE = 10*1024*1024;// 缓冲区大小为3M
		final long mapedSize=1024*1024*1024L;
		//File f = new File(path);
		
		//FileChannel channel=new RandomAccessFile(f, "rw").getChannel();
		FileChannel channel=new FileInputStream(path).getChannel();
	    long channelSize=  channel.size();
	    
	    System.out.println("mapedSize="+mapedSize);
	    if(channelSize>Integer.MAX_VALUE){
	    	System.out.println(true);
	    }else{
	    	System.out.println(false);
	    }
	    
		long start = System.currentTimeMillis();
		
		
		for(long j=0;j<channelSize;j+=mapedSize){
			long temp=channelSize-channel.position()>mapedSize?mapedSize:channelSize-channel.position();
			MappedByteBuffer inputBuffer =channel.map(FileChannel.MapMode.READ_ONLY, j, temp);
			
			byte[] dst = new byte[BUFFER_SIZE];// 每次读出的内容
			
			for (int offset = 0; offset < inputBuffer.capacity(); offset += BUFFER_SIZE) {
				if (inputBuffer.capacity() - offset >= BUFFER_SIZE) {
					for (int i = 0; i < BUFFER_SIZE; i++){
						dst[i] = inputBuffer.get(offset + i);
					}
				} else {
					for (int i = 0; i < inputBuffer.capacity() - offset; i++){
						dst[i] = inputBuffer.get(offset + i);
					}
				}

				int length = (inputBuffer.capacity() % BUFFER_SIZE == 0) ? BUFFER_SIZE
						: inputBuffer.capacity() % BUFFER_SIZE;
                
				//System.out.println(new String(dst, 0, length));// new
				// String(dst,0,length)这样可以取出缓存保存的字符串，可以对其进行操作

			}

		}
		
		if(channel!=null){
			channel.close();
		}
		
		long end = System.currentTimeMillis();

		System.out.println("读取文件文件内容花费：" + (end - start) + "毫秒");

	}


	static void largeFileIO6(String path) throws Exception{
		    ByteBuffer byteBuf = ByteBuffer.allocate(1024 * 14 * 1024);  
	        byte[] bbb = new byte[14 * 1024 * 1024];  
	        FileInputStream fis = new FileInputStream(path);  
	      
	        FileChannel fc = fis.getChannel();  
	        long timeStar = System.currentTimeMillis();// 得到当前的时间  
	       // fc.read(byteBuf);// 1 读取  
	        MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());  
	        System.out.println(fc.size()/1024);  
	        long timeEnd = System.currentTimeMillis();// 得到当前的时间  
	        System.out.println("Read time :" + (timeEnd - timeStar) + "ms");  
	        timeStar = System.currentTimeMillis();  
	        
	        FileOutputStream fos = new FileOutputStream("e://data/other/outFile.txt");  
	        fos.write(bbb);//2.写入  
	        //mbb.flip();  
	        timeEnd = System.currentTimeMillis();  
	        System.out.println("Write time :" + (timeEnd - timeStar) + "ms");  
	        fos.flush();  
	        fc.close();  
	        fis.close();  
	}
	/**
	 * map太大，不行，读不出
	 * 
	 * @param path
	 * @throws Exception
	 */
	static void largerFileIO42(String path) throws Exception {

		final int BUFFER_SIZE = 0x300000;// 缓冲区大小为3M
		File f = new File(path);
		/**
		 * map(FileChannel.MapMode mode,long position, long size)
		 * 
		 * mode - 根据是按只读、读取/写入或专用（写入时拷贝）来映射文件，分别为 FileChannel.MapMode 类中所定义的
		 * READ_ONLY、READ_WRITE 或 PRIVATE 之一
		 * 
		 * position - 文件中的位置，映射区域从此位置开始；必须为非负数
		 * 
		 * size - 要映射的区域大小；必须为非负数且不大于 Integer.MAX_VALUE
		 * 
		 * 所以若想读取文件后半部分内容，如例子所写；若想读取文本后1/8内容，需要这样写map(FileChannel.MapMode.
		 * READ_ONLY, f.length()*7/8,f.length()/8)
		 * 
		 * 想读取文件所有内容，需要这样写map(FileChannel.MapMode.READ_ONLY, 0,f.length())
		 * 
		 */
		FileChannel channel=new RandomAccessFile(f, "r").getChannel();
		System.out.println(channel.size());
		MappedByteBuffer inputBuffer =channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
		byte[] dst = new byte[BUFFER_SIZE];// 每次读出3M的内容
		long start = System.currentTimeMillis();
		for (int offset = 0; offset < inputBuffer.capacity(); offset += BUFFER_SIZE) {
			if (inputBuffer.capacity() - offset >= BUFFER_SIZE) {
				for (int i = 0; i < BUFFER_SIZE; i++)
					dst[i] = inputBuffer.get(offset + i);
			} else {
				for (int i = 0; i < inputBuffer.capacity() - offset; i++)
					dst[i] = inputBuffer.get(offset + i);
			}

			int length = (inputBuffer.capacity() % BUFFER_SIZE == 0) ? BUFFER_SIZE
					: inputBuffer.capacity() % BUFFER_SIZE;

			System.out.println(new String(dst, 0, length));// new
			// String(dst,0,length)这样可以取出缓存保存的字符串，可以对其进行操作

		}

		long end = System.currentTimeMillis();

		System.out.println("读取文件文件内容花费：" + (end - start) + "毫秒");

	}
	public static void main(String[] args) throws Exception {
		// largeFileIO(filePath,"");

		// ------------------
		// try {
		 //largerFileIO2();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// ---------------
		 //largerFileIO3(filePath);

		// -----------------------
		 largerFileIO4(filePath);

		// -------------
		//largerFileIO5(filePath);
		 
		
	}

}
