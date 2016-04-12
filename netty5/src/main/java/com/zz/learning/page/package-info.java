
/**数据库查询分页参数类
 * 
         对于oracle 数据库它的物理分页sql语句：
  
    SELECT t2.* 
      FROM ( 
            SELECT t1.*, ROWNUM rownum_ 
            FROM ( 
                select user_id,user_name from user t where t.regist_time>#time# and 
                ) t1 
            ) t2 
     WHERE rownum_ > #startIndex# AND rownum_ <= #endIndex#
 
     对于mysql数据库，物理分页sql语句
  select user_id,user_name from user t where t.regist_time>#time# limit #startIndex#,#endIndex#
  
  
 * @author sunff
 *
 */
package com.zz.learning.page;


