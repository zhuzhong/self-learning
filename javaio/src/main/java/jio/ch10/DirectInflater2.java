/**
 * 
 */
package jio.ch10;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.zip.Inflater;



/**
 * @author snoopy
 *
 */
public class DirectInflater2 {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws Exception {
		FileInputStream fis=new FileInputStream("test.zip");
		FileOutputStream fos=new FileOutputStream("test.tt");
		
		byte [] input=new byte[1024];
		byte[] output=new byte[1024];	
		Inflater inflater=new Inflater(true);
		while(true){
			
			int readNum=0;
			readNum=fis.read(input);
			if(readNum!=-1){
				inflater.setInput(input, 0, readNum);
			}
			
			int compressedNum=0;
			while((compressedNum=inflater.inflate(output, 0, output.length))!=0){
				fos.write(output, 0, compressedNum);
			}
			
			if(inflater.finished()){
				break;
			}else if(inflater.needsDictionary()){
				break;
			}else if(inflater.needsInput()){
				continue;
			}
		}
		
		fis.close();
		fos.flush();
		fos.close();
		//inflater.reset();
		inflater.end();
	

	}

}
