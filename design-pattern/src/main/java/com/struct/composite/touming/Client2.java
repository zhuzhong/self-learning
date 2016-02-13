package com.struct.composite.touming;

public class Client2 {
	public static void main(String[] args) {
		Component2 root = new Composite2("服装");
		Component2 c1 = new Composite2("男装");
		Component2 c2 = new Composite2("女装");

		Component2 leaf1 = new Leaf2("衬衫");
		Component2 leaf2 = new Leaf2("夹克");
		Component2 leaf3 = new Leaf2("裙子");
		Component2 leaf4 = new Leaf2("套装");

		root.addChild(c1);
		root.addChild(c2);
		c1.addChild(leaf1);
		c1.addChild(leaf2);
		c2.addChild(leaf3);
		c2.addChild(leaf4);

		root.printStruct("");
	}
}