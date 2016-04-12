package com.zz.learning.page;

import java.io.Serializable;

public class PageParam implements Serializable {

	private static final long serialVersionUID = -6729261950093637302L;

	public static final int DEFAULT_PAGE_SIZE = 1000; // 每页显示的记录数
	public static final int START_PAGE_INDEX = 1; // 首页页号
	protected int pageSize = DEFAULT_PAGE_SIZE;

	protected int totalCount = 0; // 记录的总数

	protected int pageCount = 0; // 总页数

	protected int startIndex = 0; // 分页开始记录数 从0开始

	public PageParam(int totalCount) {
		this(totalCount, 0, DEFAULT_PAGE_SIZE);
	}

	public PageParam(int totalCount, int pageSize) {
		this(totalCount, 0, pageSize);
	}

	public PageParam(int totalCount, int startIndex, int pageSize) {
		setPageSize(pageSize);
		setStartIndex(startIndex);
		setTotalCount(totalCount);
	}

	public PageParam(PageParam pagination) {
		this(pagination.getTotalCount(), pagination.getStartIndex(), pagination
				.getPageSize());
	}

	public int getCurrentPage() {
		if (this.startIndex < 0 || this.totalCount < 1)
			return 0;
		return (this.startIndex / this.pageSize) + 1;
	}

	protected void calculatePageCount() {
		if (this.totalCount < 1)
			this.pageCount = 0;
		else {
			if (this.totalCount % this.pageSize > 0)
				this.pageCount = (this.totalCount / this.pageSize) + 1;
			else {
				this.pageCount = this.totalCount / this.pageSize;
			}
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		if (totalCount > 0) {
			this.totalCount = totalCount;
		} else {
			this.totalCount = 0;
		}
		calculatePageCount();
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public int getEndIndex() {
		int endIndex = getStartIndex() + this.pageSize;
		if (this.totalCount > 0 && endIndex > this.totalCount) {
			endIndex = this.totalCount;
		}
		return endIndex;
	}

	public int getLastStartIndex() {
		if (this.pageCount < 1)
			return 0;
		return (this.pageCount - 1) * this.pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public int getNextIndex() {
		int nextIndex = getStartIndex() + this.pageSize;
		if (nextIndex >= this.totalCount)
			return getStartIndex();
		else
			return nextIndex;
	}

	public int getPreviousIndex() {
		int previousIndex = getStartIndex() - this.pageSize;
		if (previousIndex < 0)
			return 0;
		else
			return previousIndex;
	}

	public boolean hasPreviousPage() {
		return getStartIndex() >= 1;
	}

	public boolean hasNextPage() {
		if (this.pageCount < 1
				|| getStartIndex() == (this.pageCount - 1) * this.pageSize)
			return false;
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PageParam))
			return false;
		PageParam another = (PageParam) obj;
		return (getPageSize() == another.getPageSize())
				&& (getStartIndex() == another.getStartIndex())
				&& (getTotalCount() == another.getTotalCount());
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = hash * 31 + this.getPageSize();
		hash = hash * 31 + this.getStartIndex();
		hash = hash * 31 + this.getTotalCount();
		return hash;
	}

	@Override
	public String toString() {
		return "[pagesize = " + this.getPageSize() + ", startIndex = "
				+ this.startIndex + ", endIndex = " + this.getEndIndex()
				+ ", pageCount = " + +this.getPageCount() + ", totalcount = "
				+ this.getTotalCount() + ", currentpage = "
				+ +this.getCurrentPage() + "]";
	}
}