package com.proxy;

import java.lang.reflect.Proxy;

import com.proxy.AccountImpl;
import com.proxy.Accout;
import com.proxy.AccoutInvocationHandler;

public class ProxyTest {

	public static void main(String[] args) {
		
		
		Accout a=(Accout) Proxy.newProxyInstance(Accout.class.getClassLoader(), 
				new Class[]{Accout.class}, new AccoutInvocationHandler(new AccountImpl()));
         a.addAcount();
	}

}
