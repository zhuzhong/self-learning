/**
 * 
 */
package com.j1.server.service.support;

import org.springframework.stereotype.Service;

import com.j1.server.service.HelloService;

/**
 * @author Administrator
 *
 */

public class HelloServiceImpl implements HelloService {

	public String sayHello(String name) {
	       
		return "hello " + name;
	}

}
