/**
 * 
 */
package com.zz.learning.sl;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @author sunff
 *
 */
@Service
public class UserServiceImpl implements UserService, ApplicationContextAware {

	@Override
	public Long register(User u) {
		try {
			TimeUnit.SECONDS.sleep(2);  //用于模拟注册代码
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		u.setUserId(10000L);
		UserRegisterEvent event = new UserRegisterEvent(u);
		this.applicationContext.publishEvent(event);
		return u.getUserId();
	}

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;

	}

}
