package ch18;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FibonacciImpl extends UnicastRemoteObject implements Fibonacci {

	public FibonacciImpl() throws RemoteException {
		super();
	}

	@Override
	public BigInteger getFibonacci(int n) throws RemoteException {
		return this.getFibonacci(new BigInteger(Long.toString(n)));
	}

	@Override
	public BigInteger getFibonacci(BigInteger n) throws RemoteException {

		System.out.println("Calculating the " + n + "the Fibonacci number");
		BigInteger zero = new BigInteger("0");
		BigInteger one = new BigInteger("1");

		if (n.equals(zero))
			return one;
		if (n.equals(one))
			return one;

		BigInteger i = one;
		BigInteger low = one;
		BigInteger high = one;

		while (i.compareTo(n) == -1) {
			BigInteger temp = high;
			high = high.add(low);
			low = temp;
			i = i.add(one);
		}

		return high;

	}
}
