package com.behaviour.visitor;

/*
 * node接受visitor的访问完成一次运态分派，并将自己传给vistor，而vistor又完成一次运态分分派
 */
public class Client {

    public static void main(String[] args) {
        //创建一个结构对象
        ObjectStructure os = new ObjectStructure();
        //给结构增加一个节点
        os.add(new NodeA());
        //给结构增加一个节点
        os.add(new NodeB());
        //创建一个访问者
        Visitor visitor = new VisitorA();
        os.action(visitor);
    }

}