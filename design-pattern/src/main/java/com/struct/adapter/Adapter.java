package com.struct.adapter;
public class Adapter  implements Target {
	
	private Adaptee adaptee;
	public Adapter( Adaptee adaptee){
		this.adaptee=adaptee;
	}
    /**
     * 由于源类Adaptee没有方法sampleOperation2()
     * 因此适配器补充上这个方法
     */
    @Override
    public void sampleOperation2() {
        //写相关的代码
    	
    	System.out.println("this is sampleOperation2 method from Adapter ");
    }

	@Override
	public void sampleOperation1() {
		adaptee.sampleOperation1();
		
	}

}