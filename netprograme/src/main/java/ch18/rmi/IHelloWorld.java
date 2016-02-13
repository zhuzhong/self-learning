package ch18.rmi;
public interface IHelloWorld {  
	
    public String helloWorld();  
  
    public String sayHelloToSomeBody(String someBodyName);  
    
    public String getUserString(String userName,String password);
    
    
    public User getUser(String userName,String password);
}  