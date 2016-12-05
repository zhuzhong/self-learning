/**
 * 
 */
package com.zz.learning.page;

/**
 * 分页使用，条件 rownum>startIndex and rownum<=endIndex
 * @author sunff
 *
 */
public class Pagination {

	public static final int DEFAULT_PAGE_SIZE = 20;
	private final int pageSize; // 单页数据行数

	public Pagination() {
		this.pageSize = DEFAULT_PAGE_SIZE;
	}

	public Pagination(int pageSize) {
		this.pageSize = pageSize;
	}

	private int totalCount; // 总数据行数
	private int pageNum;// 页数

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getPageCount() {
		// 总页数
		if (totalCount % pageSize == 0) {
			return totalCount / pageSize;

		} else {
			return totalCount / pageSize + 1;
		}
	}

	public int getStartIndex() {
		return pageNum * pageSize;
	}

	public int getEndIndex() {
		int retVal = getStartIndex() + pageSize;
		if (retVal > totalCount) {
			return totalCount;
		}
		return retVal;
	}

	@Override
	public String toString() {
		return "Pagination [pageSize=" + pageSize + ", totalCount="
				+ totalCount + ", pageNum=" + pageNum

				+ ", getPageCount()=" + getPageCount() + ", getStartIndex()="
				+ getStartIndex() + ", getEndIndex()=" + getEndIndex() + "]";
	}

}
