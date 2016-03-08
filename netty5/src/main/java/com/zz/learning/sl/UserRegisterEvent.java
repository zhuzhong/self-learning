package com.zz.learning.sl;

import org.springframework.context.ApplicationEvent;

public class UserRegisterEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4500628285523118370L;
	private User user;

	public User getUser() {
		return this.user;
	}

	public UserRegisterEvent(User source) {
		super(source);
		this.user = source;
	}

}
