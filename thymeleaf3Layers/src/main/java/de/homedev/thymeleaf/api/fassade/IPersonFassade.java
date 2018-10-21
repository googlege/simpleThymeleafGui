package de.homedev.thymeleaf.api.fassade;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import de.homedev.thymeleaf.api.model.PersonEntity;
import de.homedev.thymeleaf.api.model.SearchDto;

public interface IPersonFassade extends Remote {
	public static final String SERVICE_NAME = "personFassadeImpl";
	public static final String RMI_SERVICE_NAME = "personFassadeRmi";

	public PersonEntity save(PersonEntity person) throws RemoteException;

	public void deleteById(Long pk) throws RemoteException;

	public List<PersonEntity> findAll() throws RemoteException;

	public Page<PersonEntity> findAll(Pageable pageable) throws RemoteException;

	public List<PersonEntity> findAll(SearchDto searchDto) throws RemoteException;

	public Page<PersonEntity> findAll(SearchDto searchDto, Pageable pageable) throws RemoteException;

	public PersonEntity getOne(Long id) throws RemoteException;
}
