package de.homedev.thymeleaf.frontend.service;

import java.rmi.RemoteException;

import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.homedev.thymeleaf.api.exceptions.UsernameNotFoundBackendException;
import de.homedev.thymeleaf.api.fassade.IUserFassade;
import de.homedev.thymeleaf.frontend.util.RmiClientUtil;

@Service(IUserAuthService.SERVICE_NAME)
public class UserAuthServiceImpl implements IUserAuthService {
	private static Logger log = LoggerFactory.getLogger(UserAuthServiceImpl.class);
	private IUserFassade userFassade = null;
	private final ConfigurableEnvironment environment;

	public UserAuthServiceImpl(@Autowired ConfigurableEnvironment environment) {
		this.environment = environment;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (userFassade == null) {
			int port = Integer.valueOf(environment.getRequiredProperty("app.rmi.port"));
			String rmiHost = environment.getRequiredProperty("app.rmi.host");
			log.info("rmi server port: " + port + " host: " + rmiHost);
			try {
				userFassade = RmiClientUtil.getUserFassade(port, rmiHost);
			} catch (NamingException e) {
				log.error(e.getMessage(), e);
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		try {
			UserDetails result = userFassade.loadUserByUsername(username);
			log.info(result.toString());
			return result;
		} catch (UsernameNotFoundBackendException e) {
			log.error(e.getMessage(), e);
			throw new UsernameNotFoundException(e.getMessage(), e);

		} catch (RemoteException e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}

	}

}
