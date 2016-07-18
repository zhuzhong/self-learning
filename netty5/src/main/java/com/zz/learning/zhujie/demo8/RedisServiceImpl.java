/**
 * 
 */
package com.zz.learning.zhujie.demo8;

import org.springframework.stereotype.Service;

/**
 * @author Administrator
 *
 */
@Service
public class RedisServiceImpl implements RedisService {

	@Override
	public void method1() {
		System.out.println("method1");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zz.learning.zhujie.demo8.RedisService#method2()
	 */
	@Override
	public void method2() {
		// TODO Auto-generated method stub
		System.out.println("method2");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zz.learning.zhujie.demo8.RedisService#method3()
	 */
	@Override
	public void close() {
		// TODO Auto-generated method stub
		System.out.println("close 方法");
	}

}
