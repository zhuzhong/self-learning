/**
 * 
 */
package ch06;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**利用queue完成与pooledWeblog同样的功能
 * @author snoopy
 *
 */
public class PooledWeblog2 {

	
	
	private BufferedReader in;
	private BufferedWriter out;
	private int numberOfThreads;
	private Queue<String> entries  ; //= Collections.synchronizedList(new LinkedList<String>());
	
	private boolean finished = false;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
