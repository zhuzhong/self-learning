/**
 * 
 */
package com.zz.learning.jxl;

import java.util.ArrayList;
import java.util.List;

import com.zz.learning.zxing.MatrixToImageWriter;
import com.zz.learning.zxing.QRCodeUtil;

/**
 * @author Administrator
 *
 */
public class TaskTest {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

	   /* 
	    String text="http://www.j1.com/product/114617-125394.html?union=814.925fromSite=201608hubei";
	    text="http://m.j1.com"+text.substring("http://www.j1.com".length());
	    text=text.substring(0, text.indexOf("fromSite"))+"&"+text.substring(text.indexOf("fromSite"));
	    System.out.println(text);*/
	    test();
	    //single();
	}

	
/*	private static void single()throws Exception{
	    //http://www.j1.com/product/114591-125368.html?union=813.924fromSite=201608huabei
	    
         String text = "http://www.j1.com/product/114591-125368.html?union=813.924&fromSite=201608huabei";
         text="http://m.j1.com"+text.substring("http://www.j1.com".length());
         String destPath = "d:/test/" + "114591-125368" + ".jpg";
         // QRCodeUtil.encode(text, "c:/df.jsp", "c:/a/", true);
         QRCodeUtil.encode(text, null, destPath, true);
         
         System.out.println("oook");
	}*/
	private static void test()throws Exception{
	    String []str={"sanjiu","huabei"};
	    for(String name:str){
	      // String name="zhejiang";
	       String source = "d:/test22/"
	               + name
	               + ".xls";
	        JxlTest t = new JxlTest();
	        List<Bean> result = new ArrayList<Bean>();
	        t.xls2Bean(source, result);

	        for (Bean b : result) {
	            String text = b.getC3();
	            text="http://"+text;
	           // text="http://m.j1.com"+text.substring("http://www.j1.com".length());
	           // text=text.substring(0, text.indexOf("fromSite"))+"&"+text.substring(text.indexOf("fromSite"));
	            System.out.println(text);
	            String destPath = "d:/test22/"
	                    + name
	                    + "/" + b.getC1()+"-"+ b.getC2() + ".jpg";
	            // QRCodeUtil.encode(text, "c:/df.jsp", "c:/a/", true);
	            QRCodeUtil.encode(text, null, destPath, true);
	            
	    /*      //彩色
	            destPath= "d:/test/color/" + b.getC2() + ".jpg";
	            MatrixToImageWriter.encode(text, 512, 512,null,destPath);*/
	            
	        }
	    }
	        System.out.println("oook");
	}
	
	
}
