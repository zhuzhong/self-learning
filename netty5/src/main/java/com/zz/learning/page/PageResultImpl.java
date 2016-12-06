/**
 * 
 */
package com.zz.learning.page;

import java.util.List;

/**
 * @author sunff
 *
 */
public class PageResultImpl<T> implements PageResult<T> {

	private List<T> data;
	private PageParam p;

	public PageResultImpl(List<T> data,  PageParam p) {
		this.data = data;
		this.p = p;
	}

	@Override
	public List<T> getData() {
		return data;
	}

	@Override
	public int getPageSize() {
		return p.getPageSize();
	}

	@Override
	public int getTotalCount() {
		return p.getTotalCount();
	}

	@Override
	public int getPageCount() {
		return p.getPageCount();
	}

}
