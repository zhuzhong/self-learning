package javathreads.examples.ch06.concu.test;

public class FinallyTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		FinallyTest t =new FinallyTest();
		t.test();
	}

	
	private void test() throws Exception{
		int i=0;
		int j=9;
		int k;
		try{
			k=j/i;
		}catch(Exception e){
			System.out.println(e.getStackTrace());
			throw new RuntimeException();
		}finally{
			System.out.println("ooook");
		}
	}
}
