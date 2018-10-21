package de.homedev.thymeleaf.backend.fassade;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.homedev.thymeleaf.api.exceptions.BackendException;
import de.homedev.thymeleaf.api.exceptions.UsernameNotFoundBackendException;
import de.homedev.thymeleaf.api.fassade.IUserFassade;
import de.homedev.thymeleaf.api.model.UserEntity;
import de.homedev.thymeleaf.api.model.UserRoleEntity;
import de.homedev.thymeleaf.backend.service.api.IUserService;

@Service(IUserFassade.SERVICE_NAME)
public class UserFassadeImpl implements IUserFassade {
	private static Logger log = LoggerFactory.getLogger(UserFassadeImpl.class);

	@Autowired
	private IUserService userService;

	@Override
	public UserEntity saveUser(UserEntity user) throws BackendException {
		try {
			return userService.saveUser(user);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BackendException(e.getMessage(), e);
		}
	}

	@Override
	public UserRoleEntity saveAuthority(UserRoleEntity entity) throws BackendException {
		try {
			return userService.saveAuthority(entity);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BackendException(e.getMessage(), e);
		}
	}

	@Override
	public void withdrawAuthorityByUser(UserEntity userEntity, UserRoleEntity authorityEntity) throws BackendException {
		try {
			userService.withdrawAuthorityByUser(userEntity, authorityEntity);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BackendException(e.getMessage(), e);
		}

	}

	@Override
	public List<UserEntity> findUser(String username, String userPassword) throws BackendException {
		try {
			return userService.findUser(username, userPassword);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BackendException(e.getMessage(), e);
		}
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@Override
	public List<UserRoleEntity> findByAuthorityName(String authorityName) throws BackendException {
		try {
			return userService.findByAuthorityName(authorityName);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BackendException(e.getMessage(), e);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws BackendException {
		try {
			return userService.loadUserByUsername(username);

		} catch (UsernameNotFoundException e) {
			log.error(e.getMessage(), e);
			throw new UsernameNotFoundBackendException(e.toString());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BackendException(e.getMessage(), e);
		}
	}

}
