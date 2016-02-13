/**
 * 
 */
package ch18.rmi;

import java.io.Serializable;

/**
 * @author snoopy
 *
 */
public class User  implements Serializable{

	
	private String userName;
	private String password;
	
	
	
	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}



	public String getUserName() {
		return userName;
	}



	public String getPassword() {
		return password;
	}



	public String toString(){
		return "userName is "+this.userName+" and password is "+this.password;
	}
}
