/* 
 **Utility.java 
 */
package ch15.d2;

import java.util.concurrent.TimeUnit;

public class Utility {
    public Utility() {
    }

    public static void sleepMicroSeconds(int nSecond) {
        /*try {
            Thread.sleep(nSecond);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        try {
            TimeUnit.MICROSECONDS.sleep(nSecond);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void log(String sMsg) {
        System.out.println(sMsg);
    }

    public static void log(int sMsg) {
        System.out.println(sMsg);
    }
}
