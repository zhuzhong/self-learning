package com.struct.flyweight;

public class FlavorCoffee extends Coffee {
	public String flavor;

	// 获取咖啡口味
	public FlavorCoffee(String flavor) {
		this.flavor = flavor;
	}

	@Override
	public void sell() {
		System.out.println("卖出一份" + flavor + "的咖啡。");
	}
}