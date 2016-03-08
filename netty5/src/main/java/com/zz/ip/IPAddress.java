package com.zz.ip;

public class IPAddress {

	
	private String code;
	
	
	private Address data;


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public Address getData() {
		return data;
	}


	public void setData(Address data) {
		this.data = data;
	}
	
	public String toString(){
		return "[code="+code+",data="+data+"]";
	}
	
}
