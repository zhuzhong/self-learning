/**
 * 
 */
package ch18.rmi;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

/**
 * @author snoopy
 *
 */
public class RmiRegistry implements Registry {


	@Override
	public Remote lookup(String name) throws RemoteException,
			NotBoundException, AccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void bind(String name, Remote obj) throws RemoteException,
			AlreadyBoundException, AccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void unbind(String name) throws RemoteException, NotBoundException,
			AccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void rebind(String name, Remote obj) throws RemoteException,
			AccessException {
		// TODO Auto-generated method stub

	}


	@Override
	public String[] list() throws RemoteException, AccessException {

		return null;
	}

}
