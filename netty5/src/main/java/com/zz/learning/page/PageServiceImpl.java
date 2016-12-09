/**
 * 
 */
package com.zz.learning.page;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 *
 */
public class PageServiceImpl<T> implements PageService<T> {

    public PageResult<T> queryPage(PageParam p, Map<String, Object> businessParam) {

        String totalSqlId=(String)businessParam.get("totalSqlId");
       // int pageSize=pqp.getPageSize();
        //根据totalSqlId及businessParam获取到总记录数，然后初始化PageParam
       p.setTotalCount(1000);
        businessParam.put("startIndex", p.getStartIndex());
        businessParam.put("endIndex", p.getEndIndex());
        
        String querySqlId=(String)businessParam.get("querySqlId");
        /**
         * 根据querySqlId，businessParam获取分页数据
         */
        List<T> data=null;
        
        PageResult<T> result=
                new PageResultImpl<T>(data, p);
        return result;
    }

}
