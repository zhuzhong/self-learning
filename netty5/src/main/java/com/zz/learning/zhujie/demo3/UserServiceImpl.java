/**
 * 
 */
package com.zz.learning.zhujie.demo3;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@UserAnnotation
	@Override
	public void queryUser(Long userId) {

		System.out.println("queryUser userId=" + userId);
		insertUser(userId);
		((UserService) AopContext.currentProxy()).insertUser(userId);
	}

	@UserAnnotation
	@Override
	public void insertUser(Long userId) {
		System.out.println("insertUser userId=" + userId);
	}

}
