/**
 * 
 */
package com.zz.learning.page;

import java.util.List;
import java.util.Map;

import c.z.zcommon.page.PageParam;
import c.z.zcommon.page.PageResult;
import c.z.zcommon.page.PageService;

/**
 * @author Administrator
 *
 */
public class PageServiceTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

        int pageSize = 100;
        PageParam p = new PageParam(0, 0, pageSize);

        PageService<User> pageService = null;
        Map<String, Object> businessParams = null;
        PageResult<User> pageUsers = pageService.queryPage(p, businessParams);

        List<User> users = pageUsers.getData();

        if (pageUsers.getPageCount() > 1) {
            for (int pagenum = 1; pagenum < pageUsers.getPageCount(); pagenum++) {
                p.setStartIndex(pagenum * pageSize);
                pageUsers = pageService.queryPage(p, businessParams);
                users = pageUsers.getData();
            }
        }
    }

}
