/**
 * 
 */
package test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * @author snoopy
 *
 */
public class PrintStreamTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		try {
//			PrintStream out=new PrintStream(new FileOutputStream("J:/workspace/network/net3programe/src/javaio/test/writetest.ba"));
//			for (int i = 0; i <= 127; i++) out.write(i);
//            
//			
//			PrintStream out2=new PrintStream(new FileOutputStream("J:/workspace/network/net3programe/src/javaio/test/printtest.ba"));
//			for (int i = 0; i <= 127; i++) out2.print(i);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		float inches=10;
		//System.out.printf("There are %f centimeters in %f inches.", 2.54*inches, inches);

		
		for (double degrees = 0.0; degrees < 360.0; degrees++) {
			  double radians = Math.PI * degrees / 180.0;
			  double grads = 400 * degrees / 360;
			  System.out.printf("%5.1f %5.1f %5.1f\n", degrees , radians, grads);
			}
		
		//String lineBreak = System.getProperty("line.separator");
		//System.out.println(lineBreak);
      //  System.out.println("over...");
	}

}
