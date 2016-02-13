package ch18.rmi;

import java.rmi.server.RemoteServer;
import java.util.Set;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class SecurityInterceptor implements MethodInterceptor {
	private Set allowed;

	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		String clientHost = RemoteServer.getClientHost();
		System.out.println("clientHostip="+clientHost);
		if (allowed != null && allowed.contains(clientHost)) {
			return methodInvocation.proceed();
		} else {
			throw new SecurityException("非法访问。");
		}
	}

	public void setAllowed(Set allowed) {
		this.allowed = allowed;
	}
}
