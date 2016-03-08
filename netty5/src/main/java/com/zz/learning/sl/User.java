package com.zz.learning.sl;

 class User {

	private String userName;
	private String pass;
	
	private Long userId;
	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	
	public String toString(){
		return "[userName="+this.userName+",password="+this.pass+",userId="+userId
				+ "]";
	}
}
