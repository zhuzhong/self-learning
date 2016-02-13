package ch18.rmi;

public class HelloWorld implements IHelloWorld {

	@Override
	public String helloWorld() {
		return "Hello World!";
	}

	@Override
	public String sayHelloToSomeBody(String someBodyName) {
		return "Hello World!" + someBodyName;
	}
	@Override
	 public String getUserString(String userName,String password){
		 return new User(userName,password).toString();
	 }

	
	 public User getUser(String userName,String password){
		 return new User(userName,password);
	 }
}