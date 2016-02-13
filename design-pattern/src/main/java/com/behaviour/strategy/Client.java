package com.behaviour.strategy;

public class Client {

	public static void main(String[] args) {
		Context c=new Context(new ConcreteStrategyB());
		c.contextInterface();

	}

}
