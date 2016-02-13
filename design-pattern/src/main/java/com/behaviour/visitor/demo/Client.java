package com.behaviour.visitor.demo;

//http://www.cnblogs.com/java-my-life/archive/2012/06/14/2545381.html

//运态双重分派
public class Client {

	public static void main(String[] args) {
		// 组合1
		East east = new SubEast1();
		West west = new SubWest1();
		east.goEast(west);
		// 组合2
		// east = new SubEast1();
		// west = new SubWest2();
		// east.goEast(west);
	}

}