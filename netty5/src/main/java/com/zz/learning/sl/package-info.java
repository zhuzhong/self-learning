
/**
 * @author sunff
 *spring 的监听器模式测试
 *
 * 但是这个有一个坑，那就是当被实现了ApplicationListener,被注入了父容器中applicationContext，
 * 如果在web(springmvc )环境中被触发，可能会被执行两次，原因是springmvc
 * 它有一个子容器applicationContext, 它继承了父容器的一切，
 * 如果将相应的ApplicationListener只注入子容器，有可能不会被触发，原因是
 * 有事情件只在父容器中发出，比如定时的job。
 * 我现的解决的方案，将父容器的类全部降级至子容器中，也就是在容器初始化时，父容器是空的，而所有的业务类注册 
 * 全部到子容器中，这样就可以了。
 *
 */
package com.zz.learning.sl;