package com.single;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
public class FileTest {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File file = new File("G://DataOutputStream.txt");
		//File file2 = new File("G://OutputStreamWriter.txt");
		try {
			//file.createNewFile();
			DataOutputStream output = new DataOutputStream(new FileOutputStream(file));
			String s="Thisisateststring";
			//for(int i=0;i<s.length();i++)
			output.writeUTF(s);
			
			//output.writeUTF("b");
			output.flush();
			//file2.createNewFile();
			
//			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file2),"utf-8");
//			writer.write("a");
//			writer.write("b");
//			writer.flush();
			output.close();
			//writer.close();
			System.out.println("oook...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*for(File f:file.listRoots()){
			System.out.println(f);
		}*/
	}
}