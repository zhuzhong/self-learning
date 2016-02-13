package com.struct.bridge;

public class Client {

	public static void main(String[] args) {

		Vehicle v = new Car(new Street());
		v.run();
	}

}
