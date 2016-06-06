/**
 * 
 */
package com.zz.learning.zhujie.demo1;

/**
 * @author Administrator
 *
 */
public class ForumService {

	@NeedTest
	public void deleteForum(int forumId) {
		System.out.println("删除论坛模块");
	}

	@NeedTest(false)
	public void deleteTopic(int postId) {
		System.out.println("删除论坛topic");
	}
}
