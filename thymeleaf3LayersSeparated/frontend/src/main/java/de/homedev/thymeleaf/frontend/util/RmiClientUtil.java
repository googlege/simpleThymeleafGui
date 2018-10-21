package de.homedev.thymeleaf.frontend.util;

import javax.naming.NamingException;

import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import de.homedev.thymeleaf.api.fassade.IPersonFassade;
import de.homedev.thymeleaf.api.fassade.IUserFassade;

public final class RmiClientUtil {
	private RmiClientUtil() {
	}

	private static IUserFassade userFassade;
	private static IPersonFassade personFassade;

	public static IUserFassade getUserFassade(int port, String rmiHost) throws NamingException {
		if (userFassade == null) {
			RmiProxyFactoryBean rmiProxyFactory = new RmiProxyFactoryBean();
			rmiProxyFactory.setServiceUrl("rmi://" + rmiHost + ':' + port + '/' + IUserFassade.RMI_SERVICE_NAME);
			rmiProxyFactory.setServiceInterface(IUserFassade.class);
			rmiProxyFactory.afterPropertiesSet();
			userFassade = (IUserFassade) rmiProxyFactory.getObject();
		}
		return userFassade;
	}

	public static IPersonFassade getPersonFassade(int port, String rmiHost) throws NamingException {
		if (personFassade == null) {
			RmiProxyFactoryBean rmiProxyFactory = new RmiProxyFactoryBean();
			rmiProxyFactory.setServiceUrl("rmi://" + rmiHost + ':' + port + '/' + IPersonFassade.RMI_SERVICE_NAME);
			rmiProxyFactory.setServiceInterface(IPersonFassade.class);
			rmiProxyFactory.afterPropertiesSet();
			personFassade = (IPersonFassade) rmiProxyFactory.getObject();
		}
		return personFassade;
	}
}
