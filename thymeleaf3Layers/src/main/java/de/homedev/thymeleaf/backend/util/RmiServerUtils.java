package de.homedev.thymeleaf.backend.util;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import de.homedev.thymeleaf.api.fassade.IPersonFassade;
import de.homedev.thymeleaf.api.fassade.IUserFassade;

public final class RmiServerUtils {
	private RmiServerUtils() {
	}

	public static void bindUserFassade(Registry registry, IUserFassade fassadeImpl) throws RemoteException {
		IUserFassade stub = (IUserFassade) UnicastRemoteObject.exportObject(fassadeImpl, 0);
		registry.rebind(IUserFassade.RMI_SERVICE_NAME, stub);
	}

	public static void bindPersonFassade(Registry registry, IPersonFassade fassadeImpl) throws RemoteException {
		IPersonFassade stub = (IPersonFassade) UnicastRemoteObject.exportObject(fassadeImpl, 0);
		registry.rebind(IPersonFassade.RMI_SERVICE_NAME, stub);
	}

	public static Registry createRegistry(int port) throws RemoteException {
		return LocateRegistry.createRegistry(port);
	}
}
