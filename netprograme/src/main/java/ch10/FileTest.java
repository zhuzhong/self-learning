/**
 * 
 */
package ch10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * @author snoopy
 * 
 */
public class FileTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// listRoots();
		test3();
	}

	private static void test3() {
		Class cla = FileTest.class;
		ClassLoader loader = cla.getClassLoader();
		String pathName = FileTest.class.getPackage().getName();
		InputStream in = loader.getResourceAsStream(pathName + File.separator
				+ "index.html");
	}

	private static void test2() {
		System.out.println(FileTest.class.getPackage().getName());
		System.out.println(File.separator);
		System.out.println(System.getProperty("user.dir"));
		String pathName = FileTest.class.getPackage().getName();
		File f = new File("src/" + pathName + File.separator + "index.html");
		try {
			InputStream in = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void test() {
		File f = new File(
				"F:/workspace/network/net3programe/src/ch10/index.html");
		try {
			System.out.println(f.getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			InputStream in = new FileInputStream(f);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			 try {
				while (!(line = reader.readLine()).equals("")) {
				      System.out.println(line);
				  }
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void listRoots() {
		File[] files = File.listRoots();
		for (File file : files) {
			System.out.println(file);
			if (file.length() > 0) {
				String[] filenames = file.list();
				for (String filename : filenames) {
					System.out.println(filename);
				}
			}
		}
	}
}
