package com.create.builder;

/*
 *  An abstract Builder
 */
public interface HouseBuilder {
	public void buildRoom(int roomNo);

	public void buildDoor(int room1, int room2);

	public House getHouse();
}