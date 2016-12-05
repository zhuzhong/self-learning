/**
 * 
 */
package com.zz.learning.page;

/**
 * @author sunff
 *
 */
public class PaginationDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Pagination p=new Pagination();
		p.setTotalCount(203);
	
		System.out.println(p);
		while(p.hasNextPage()){
			p.nextPage();
			System.out.println(p.toString());
			
		}
		
		
		/*		for(int i=0;i<p.getPageCount();i++){
		p.setPageNum(i);
		System.out.println(p);
	}*/
		/**
		 * 
		 *  oracle 分页使用示例
		 *	select * from (select 
		 */

	}

}
