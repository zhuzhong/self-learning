/**
 * 
 */
package com.zz.learning.page;

import java.io.Serializable;
import java.util.List;

/**
 * @author sunff
 *
 */
public interface PageResult<T> extends Serializable{

	List<T> getData();

	int getPageSize();//单页行数
	int getTotalCount(); //总行数
	int getPageCount();//总页数
}
