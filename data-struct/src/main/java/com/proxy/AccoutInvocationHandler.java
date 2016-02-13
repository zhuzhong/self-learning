package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AccoutInvocationHandler implements InvocationHandler {

	
	private AccountImpl imple;
	
	public AccoutInvocationHandler(AccountImpl imple){
		this.imple=imple;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		if(proxy instanceof Accout){
			SecurityCheck.check();
		}
		return method.invoke(imple, args);
	}

}
