package com.zz.learning.sl;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service("userRegisterListener1")
public class UserRegisterListener1 implements ApplicationListener<UserRegisterEvent>{

	@Override
	public void onApplicationEvent(UserRegisterEvent event) {
		if(event.getUser()!=null){
			System.out.println(event.getUser().toString());
		}
		System.out.println("我是UserRegisterEvent 监听器1,do something...");
		
	}

}
