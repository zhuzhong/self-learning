package com.struct.flyweight;

import java.util.ArrayList;
import java.util.List;


//对象coffee共享
public class Client {
	// 客户下的订单
	private static List<Coffee> coffees = new ArrayList<Coffee>();

	// 订单对象生成工厂
	private static FlavorFactory flavorFactory;

	// 增加订单
	private static void takeOrders(String flavor) {
		coffees.add(flavorFactory.getCoffee(flavor));
	}

	public static void main(String[] args) {
		// 订单生成工厂
		flavorFactory = FlavorFactory.getInstance();

		// 增加订单
		takeOrders("摩卡");
		takeOrders("卡布奇诺");
		takeOrders("香草星冰乐");
		takeOrders("香草星冰乐");
		takeOrders("拿铁");
		takeOrders("卡布奇诺");
		takeOrders("拿铁");
		takeOrders("卡布奇诺");
		takeOrders("摩卡");
		takeOrders("香草星冰乐");
		takeOrders("卡布奇诺");
		takeOrders("摩卡");
		takeOrders("香草星冰乐");
		takeOrders("拿铁");
		takeOrders("拿铁");

		// 卖咖啡
		for (Coffee coffee : coffees) {
			coffee.sell();
		}

		// 打印生成的订单java对象数量
		System.out.println("\n客户一共买了 " + coffees.size() + " 杯咖啡! ");

		// 打印生成的订单java对象数量
		System.out.println("共生成了 " + flavorFactory.getTotalFlavorsMade()
				+ " 个 FlavorCoffee java对象! ");
	}
}