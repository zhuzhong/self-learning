
/**数据库查询分页参数类
 * 
         对于oracle 数据库它的物理分页sql语句：
  
    SELECT t2.* 
      FROM ( 
            SELECT t1.*, ROWNUM rownum_ 
            FROM ( 
                select user_id,user_name from user t where t.regist_time>#time#  
                ) t1 where ROWNUM <= #endIndex#
            ) t2 
     WHERE rownum_ > #startIndex# 
 
     对于mysql数据库，物理分页sql语句
  select user_id,user_name from user t where t.regist_time>#time# limit #startIndex#,#pageSize#
  
  
 * @author sunff
 *
 */
package com.zz.learning.page;


