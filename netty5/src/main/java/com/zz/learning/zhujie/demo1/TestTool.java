/**
 * 
 */
package com.zz.learning.zhujie.demo1;

import java.lang.reflect.Method;

/**
 * @author Administrator
 *
 */
public class TestTool {

	public static void main(String args[]) {
		Class clazz = ForumService.class;
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			NeedTest nt = method.getAnnotation(NeedTest.class);
			if (nt != null) {
				if (nt.value()) {
					System.out.println("need test--"+method.getName());
				} else {
					System.out.println("不需要test--"+method.getName());
				}
			}
		}
	}
}
