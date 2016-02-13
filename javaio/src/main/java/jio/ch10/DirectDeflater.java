package jio.ch10;

import java.io.*;
import java.util.zip.*;

/**
 * deflater 只能是自己压缩，自己解压，别的软件好像不能用
 * @author snoopy
 *
 */
public class DirectDeflater {

  public final static String DEFLATE_SUFFIX = ".dfl";

  public static void main(String[] args) throws IOException  {
  
  //  Deflater def = new Deflater();
    
    
    Deflater def = new Deflater(4,true);
    byte[] input = new byte[1024];
    byte[] output = new byte[1024];

   // for (int i = 0; i < args.length; i++) {
//        FileInputStream fin = new FileInputStream(args[i]);   
//        FileOutputStream fout = new FileOutputStream(args[i] + DEFLATE_SUFFIX);
        
        
        FileInputStream fin = new FileInputStream("I:/workspace/network/net3programe/src/javaio/jio/ch10/DirectInflater.java");   
        FileOutputStream fout = new FileOutputStream("I:/workspace/network/net3programe/src/javaio/jio/ch10/DirectInflater.zip" );
        while (true) { // read and deflate the data      

          // Fill the input array.
          int numRead = fin.read(input);
          if (numRead == -1) { // end of stream
            // Deflate any data that remains in the input buffer.
            def.finish(); 
            while (!def.finished()) {
              int numCompressedBytes = def.deflate(output, 0, output.length);
              if (numCompressedBytes > 0) {
                fout.write(output, 0, numCompressedBytes);
              } // end if
            }  // end while
            break; // Exit while loop.
          } // end if
          else {  // Deflate the input.
            def.setInput(input, 0, numRead);
            while (!def.needsInput()) {
              int numCompressedBytes = def.deflate(output, 0, output.length);
              if (numCompressedBytes > 0) {
                fout.write(output, 0, numCompressedBytes);
              } // end if
            }  // end while
          }  // end else            
        } // end while
        fin.close();
        fout.flush();
        fout.close();
        def.reset();
  //  }
    
    System.out.println("ooook");
  }
}