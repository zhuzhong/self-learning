package ch18;

import java.math.BigInteger;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Fibonacci extends Remote {

	public BigInteger getFibonacci(int n) throws RemoteException;

	public BigInteger getFibonacci(BigInteger n) throws RemoteException;

}
