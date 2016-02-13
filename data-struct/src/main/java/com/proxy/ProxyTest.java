package com.proxy;

import java.lang.reflect.Proxy;

public class ProxyTest {

	public static void main(String[] args) {
		
		
		Accout a=(Accout) Proxy.newProxyInstance(Accout.class.getClassLoader(), 
				new Class[]{Accout.class}, new AccoutInvocationHandler(new AccountImpl()));
         a.addAcount();
	}

}
