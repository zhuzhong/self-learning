package com.proxy.asm;

import java.io.IOException;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Account account = new Account();
		account.operation();
	

		SecureAccountGenerator s = new SecureAccountGenerator();
		try {
			account = s.generateSecureAccount();
			account.operation();
		} catch (ClassFormatError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
