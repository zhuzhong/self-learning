package ch18;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class FibonacciServer {

	public static void main(String[] args) {

		try {//-Djava.rmi.server.hostname=192.168.1.102
			//System.setProperty("java.rmi.server.hostname", "192.168.1.102");
			//System.setProperty("java.rmi.server.hostname", "192.168.1.102");
			FibonacciImpl f = new FibonacciImpl();
			System.out.println("Fibonacci Server ready----.");
			Naming.rebind("fibonacci", f);
			System.out.println("Fibonacci Server ready.");
		} catch (RemoteException rex) {
			System.out.println("Exception in FibonacciImpl.main: " + rex);
		} catch (MalformedURLException ex) {
			System.out.println("MalformedURLException " + ex);
		}

	}

}
