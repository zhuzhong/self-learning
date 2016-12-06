/**
 * 
 */
package com.zz.learning.page;

import java.util.List;

/**
 * @author sunff
 *
 */
public class PageParamDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PageParamDemo demo=new PageParamDemo();
		demo.test1();
	}

	public void test1() {
		/**
		 * 查询步骤,  
		 * 1.获取查询参数 pageNum,pageSize及业务参数;
		 * 2.根据业务参数，查询数据总行数，然后实例化PageParam;
		 * 3.根据计算出来的startIndex,endIndex与业务参数进行
		 * 物理分页获取相应的数据
		 */
		PageQueryParam pqp=new PageQueryParam(false);
		int pageNum=pqp.getPageNum();
		int pageSize=pqp.getPageSize();
		
		PageParam p = new PageParam(100089);
		p.setPageSize(pageSize);
		if(pqp.isPageStartFromOne()){
			p.setStartIndex(pageSize*(pageNum-1));
		}else{
			p.setStartIndex(pageSize*pageNum);
		}
		
		System.out.println(p);
		List<PageQueryParam> data=null;
		PageResult<PageQueryParam> result=
				new PageResultImpl<PageQueryParam>(data, p);
	}

	public void test2() {
		/*
		 * 对于分页查询，有两步，第一步获取总数，第二步进行分页获取
		 */
		PageParam p = new PageParam(100089);
		System.out.println(p);

		while (p.hasNextPage()) {
			p.setStartIndex(p.getNextIndex());
			int startIndex = p.getStartIndex();
			int endIndex = p.getEndIndex();

			System.out.println(p);
		}
	}
}
