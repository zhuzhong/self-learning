package ch15;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

//package java.net

public abstract class CacheResponse{

  public abstract InputStream getBody();
  public abstract Map<String,List<String>> getHeaders();

}
