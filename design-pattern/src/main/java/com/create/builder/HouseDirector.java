package com.create.builder;

/*
 *  This class is a Director ÷∏ª”’ﬂ
 */
public class HouseDirector {
	public void createHouse(HouseBuilder concreteBuilder) {
		concreteBuilder.buildRoom(1);
		concreteBuilder.buildRoom(2);
		concreteBuilder.buildDoor(1, 2);

		// return builder.getHouse();
	}
}