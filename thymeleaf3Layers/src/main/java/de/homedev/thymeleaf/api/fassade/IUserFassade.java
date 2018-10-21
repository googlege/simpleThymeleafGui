package de.homedev.thymeleaf.api.fassade;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import de.homedev.thymeleaf.api.model.UserEntity;
import de.homedev.thymeleaf.api.model.UserRoleEntity;

public interface IUserFassade extends Remote {
	public static final String SERVICE_NAME = "userFassadeImpl";
	public static final String RMI_SERVICE_NAME = "userFassadeRmi";

	public UserEntity saveUser(UserEntity user) throws RemoteException;

	public UserRoleEntity saveAuthority(UserRoleEntity entity) throws RemoteException;

	public void withdrawAuthorityByUser(UserEntity userEntity, UserRoleEntity newAuthorityEntity) throws RemoteException;

	public List<UserEntity> findUser(String username, String userPassword) throws RemoteException;

	public List<UserRoleEntity> findByAuthorityName(String authorityName) throws RemoteException;

	public UserDetails loadUserByUsername(String username) throws RemoteException;

}
