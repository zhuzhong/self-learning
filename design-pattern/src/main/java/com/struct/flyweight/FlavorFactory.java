package com.struct.flyweight;

import java.util.HashMap;
import java.util.Map;

public class FlavorFactory {
	
	private Map<String, Coffee> flavorCoffeePool = new HashMap<String, Coffee>();

	// 静态工厂,负责生成订单对象
	private static FlavorFactory flavorFactory = new FlavorFactory();

	private FlavorFactory() {
	}

	public static FlavorFactory getInstance() {
		return flavorFactory;
	}

	public Coffee getCoffee(String flavor) {
		Coffee order = null;

		if (flavorCoffeePool.containsKey(flavor)) {// 如果此映射包含指定键的映射关系，则返回 true
			order = flavorCoffeePool.get(flavor);

		} else {
			order = new FlavorCoffee(flavor);
			flavorCoffeePool.put(flavor, order);
		}
		return order;
	}

	public int getTotalFlavorsMade() {
		return flavorCoffeePool.size();
	}
}