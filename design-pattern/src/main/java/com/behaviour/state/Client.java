package com.behaviour.state;

//http://www.cnblogs.com/java-my-life/archive/2012/06/08/2538146.html
public class Client {

    public static void main(String[] args) {
        
        VoteManager vm = new VoteManager();
        for(int i=0;i<9;i++){
            vm.vote("u1","A");
        }
    }

}