/**
 * 
 */
package com.zz.learning.page;

/**
 * @author sunff
 *
 */
public class PageQueryParam {

	/**
	 * 页码从1开始 true, 从0开始 false;
	 */
	private final boolean pageStartFromOne;

	private int pageSize;
	private int pageNum;

	public PageQueryParam(boolean pageStartFromOne) {
		this.pageStartFromOne = pageStartFromOne;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public boolean isPageStartFromOne() {
		return pageStartFromOne;
	}

}
