/**
 * 
 */
package com.zz.learning.zhujie.demo8;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 *
 */
@Service
public class TestClass {

	@Autowired
	private RedisService redisService;

	public void t() {
		redisService.method1();
		redisService.method2();
	}
}
