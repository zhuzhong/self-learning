/**
 * 
 */
package com.zz.learning.page;

import java.util.Map;

/**
 * @author Administrator
 *
 */
public interface PageService<T> {
    public PageResult<T> queryPage(PageParam pqp, Map<String, Object> businessParam);
}
