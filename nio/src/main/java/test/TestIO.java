package test;

import java.io.*;

public class TestIO {
	public static void main(String[] args) throws IOException {
		// 1.以行为单位从一个文件读取数据
//		BufferedReader in = new BufferedReader(new FileReader(
//				"C:\\Users\\snoopy\\Desktop\\20141014\\Test.java"));
		BufferedReader in =new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\snoopy\\Desktop\\20141014\\Test.java"), "gbk"));
		
		String s;
		StringBuffer sb=new StringBuffer();
		String s2 = new String();
		while ((s = in.readLine()) != null) {
			sb.append(s);
			sb.append("\n");
			//s2 += s + "\n";
			System.out.println(s);
		}
        s2=sb.toString();
		in.close();
		// 1b. 接收键盘的输入
		BufferedReader stdin = new BufferedReader(new InputStreamReader(
				System.in));
		System.out.println("Enter a line:");
		System.out.println(stdin.readLine());
		// 2. 从一个String 对象中读取数据
		StringReader in2 = new StringReader(s2);
		int c;
		while ((c = in2.read()) != -1) {
			System.out.println((char) c);
		}

		in2.close();
		// 3. 从内存取出格式化输入
		try {
			DataInputStream in3 = new DataInputStream(new ByteArrayInputStream(
					s2.getBytes()));
			while (true)
				System.out.println((char) in3.readByte());
		} catch (EOFException e) {
			System.out.println("End of stream");
		}
		// 4. 输出到文件
		try {
			BufferedReader in4 = new BufferedReader(new StringReader(s2));
			PrintWriter out1 = new PrintWriter(new BufferedWriter(
					new FileWriter("C:\\Users\\snoopy\\Desktop\\TestIO.out")));
			//BufferedWriter bw=new BufferedWriter(new Filewriter("dd"));
			
			int lineCount = 1;
			while ((s = in4.readLine()) != null)
				out1.println(lineCount++ + "：" + s);
			out1.close();
			in4.close();
		} catch (EOFException ex) {
			System.out.println("End of stream");
		}
		// 5. 数据的存储和恢复
		try {
			DataOutputStream out2 = new DataOutputStream(
					new BufferedOutputStream(new FileOutputStream(
							"C:\\Users\\snoopy\\Desktop\\\\ Data.txt")));
			out2.writeDouble(3.1415926);
			out2.writeChars("\nThas was pi:writeChars\n");
			out2.writeBytes("Thas was pi:writeByte\n");
			out2.close();
			DataInputStream in5 = new DataInputStream(new BufferedInputStream(
					new FileInputStream("C:\\Users\\snoopy\\Desktop\\\\ Data.txt")));
			BufferedReader in5br = new BufferedReader(
					new InputStreamReader(in5));
			System.out.println(in5.readDouble());
			System.out.println(in5br.readLine());
			System.out.println(in5br.readLine());
		} catch (EOFException e) {
			System.out.println("End of stream");
		}
		// 6. 通过RandomAccessFile 操作文件
//		RandomAccessFile rf = new RandomAccessFile("F:\\nepalon\\rtest.dat",
//				"rw");
//		for (int i = 0; i < 10; i++)
//			rf.writeDouble(i * 1.414);
//		rf.close();
//		rf = new RandomAccessFile("F:\\nepalon\\ rtest.dat", "r");
//		for (int i = 0; i < 10; i++)
//			System.out.println("Value " + i + "：" + rf.readDouble());
//		rf.close();
//		rf = new RandomAccessFile("F:\\nepalon\\ rtest.dat", "rw");
//		rf.seek(5 * 8);
//		rf.writeDouble(47.0001);
//		rf.close();
//		rf = new RandomAccessFile("F:\\nepalon\\ rtest.dat", "r");
//		for (int i = 0; i < 10; i++)
//			System.out.println("Value " + i + "：" + rf.readDouble());
//		rf.close();
	}
}