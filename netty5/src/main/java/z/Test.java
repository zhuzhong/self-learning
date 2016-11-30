/**
 * 
 */
package z;

import java.math.BigDecimal;

/**
 * @author Administrator
 *
 */
public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) {
        BigDecimal a=new BigDecimal(100);
        
        BigDecimal b=new BigDecimal(a.doubleValue());
        b=b.add( new BigDecimal(10000));
        a=a.add(BigDecimal.ONE);
        System.out.println(a);
        
        System.out.println(b);

    }

}
