/**
 * 
 */
package com.zz.learning.page;

/**
 * @author sunff
 *
 */
public class PageParamDemo {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        /*
         * 对于分页查询，有两步，第一步获取总数，第二步进行分页获取
         */
        PageParam p = new PageParam(100089);
        System.out.println(p);
        
        while (p.hasNextPage()) {
            p.setStartIndex(p.getNextIndex());
            System.out.println(p);
        }
    }

}
