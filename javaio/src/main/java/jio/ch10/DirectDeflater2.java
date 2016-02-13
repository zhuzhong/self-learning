/**
 * 
 */
package jio.ch10;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.zip.Deflater;

/**
 * @author snoopy
 *
 */
public class DirectDeflater2 {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		FileInputStream fis=new FileInputStream("test.txt");
		FileOutputStream fos=new FileOutputStream("test.zip");
		
		byte [] input=new byte[1024];
		byte[] output=new byte[1024];	
		Deflater deflater=new Deflater();
		
		while(true){
			int readNum=fis.read(input);
			if(readNum!=-1){
				deflater.setInput(input, 0, readNum);
				while(!deflater.needsInput()){
					int numCompressedBytes =deflater.deflate(output, 0, output.length);
					if(numCompressedBytes>0){
						fos.write(output, 0, numCompressedBytes);
					}
				}
			}else{
				deflater.finish();
				while(!deflater.finished()){
					int numCompressedBytes =deflater.deflate(output, 0, output.length);
					if(numCompressedBytes>0){
						fos.write(output, 0, numCompressedBytes);
					}
				}
				break;
				
			}
		}

		
		fis.close();
		fos.flush();
		fos.close();
		deflater.end();
	}

}
