package com.test;

import java.util.Random;

/**
 * @author snoopy
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		Test t =new Test();
//		//t.test3();
//		for(int i=0;i<10;i++)
//		System.out.println(t.test(6));	
		int i=10;
		System.out.println(i>>1);
	}
	
	
	 static Random r = new Random();
	// static String ssource = "5678901234";
	 static char[] src = "5678901234".toCharArray();
	    
    //产生随机字符串
    private  String test (int length)
    {
            char[] buf = new char[length];
            int rnd;
            for(int i=0;i<length;i++)
            {
                    rnd = Math.abs(r.nextInt()) % src.length;
                    buf[i] = src[rnd];
            }
            return new String(buf);
    }

    
	
	private void test3(){
		
		java.util.Random dom = new java.util.Random();
		   int ints = dom.nextInt(100000);//6位
		   if(ints<10000){//小于6位的判断,可以取出非常整齐的全是6位的随机数
		    System.out.println(ints);//不足6位的
		   }else{
		    System.out.println("        "+ints);//6位数
		   }
		   
	}
	private String test2(){
		int[] array = {0,1,2,3,4,5,6,7,8,9};
		Random rand = new Random();
		for (int i = 10; i > 1; i--) {
		    int index = rand.nextInt(i);
		    int tmp = array[index];
		    array[index] = array[i - 1];
		    array[i - 1] = tmp;
		}
		//int result = 0;
		StringBuilder sb=new StringBuilder();
		for(int i = 0; i < 6; i++){
		  //  result = result * 10 + array[i];
		
			sb.append(array[i]);
		}
	
		//System.out.println(result);
		//System.out.println("sb.toString ="+sb.toString());
	    return sb.toString();
	}


}
