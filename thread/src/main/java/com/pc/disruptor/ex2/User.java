/**
 * 
 */
package com.pc.disruptor.ex2;

/**
 * @author sunff
 *
 */
public class User {

	
	private String userName;
	private String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String toString(){
		return this.userName+"="+this.password;
	}
}
