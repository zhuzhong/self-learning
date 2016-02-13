package com.struct.composite;
public class Client {
    public static void main(String[]args){
    	//采用自上向下的方式构建树形结构
        Composite root = new Composite("服装");
        
        
        Composite c2 = new Composite("女装");
        root.addChild(c2);
        Leaf leaf3 = new Leaf("裙子");
        Leaf leaf4 = new Leaf("套装");
        c2.addChild(leaf3);
        c2.addChild(leaf4);
        
        
        Composite c1 = new Composite("男装");   
        root.addChild(c1);
        Leaf leaf1 = new Leaf("衬衫");
        Leaf leaf2 = new Leaf("夹克");
        c1.addChild(leaf1);
        c1.addChild(leaf2);
        
       
     
        
        root.printStruct("");
    }
}