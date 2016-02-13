package ch15;

import java.io.IOException;
import java.io.OutputStream;

//package java.net

public abstract class CacheRequest {


  
 
  public abstract OutputStream getBody() throws IOException;


  public abstract void abort();

}
