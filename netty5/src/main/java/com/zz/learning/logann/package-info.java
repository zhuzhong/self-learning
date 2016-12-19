
/** 通过注解的形式的为生成相应的方法log
 * @author sunff
 *
 */
package com.zz.learning.logann;

/**
使用方法
在spring配置文件中
1.打开   <aop:aspectj-autoproxy />

2.注入bean MethodLogAspect
3.在需要打印log的方法使用注解MethodLog
*/