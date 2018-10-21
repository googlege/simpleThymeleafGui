package de.homedev.thymeleaf.backend.fassade;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import de.homedev.thymeleaf.api.exceptions.BackendException;
import de.homedev.thymeleaf.api.fassade.IPersonFassade;
import de.homedev.thymeleaf.api.model.PersonEntity;
import de.homedev.thymeleaf.api.model.SearchDto;
import de.homedev.thymeleaf.backend.service.api.IPersonService;

@Service(IPersonFassade.SERVICE_NAME)
public class PersonFassadeImpl implements IPersonFassade {
	private static Logger log = LoggerFactory.getLogger(UserFassadeImpl.class);

	@Autowired
	private IPersonService personService;

	@Override
	public PersonEntity save(PersonEntity person) throws BackendException {
		try {
			return personService.save(person);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BackendException(e.getMessage(), e);
		}
	}

	@Override
	public void deleteById(Long pk) throws BackendException {
		try {
			personService.deleteById(pk);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BackendException(e.getMessage(), e);
		}
	}

	@Override
	public List<PersonEntity> findAll() throws BackendException {
		try {
			return personService.findAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BackendException(e.getMessage(), e);
		}
	}

	@Override
	public Page<PersonEntity> findAll(Pageable pageable) throws BackendException {
		try {
			return personService.findAll(pageable);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BackendException(e.getMessage(), e);
		}
	}

	@Override
	public List<PersonEntity> findAll(SearchDto searchDto) throws BackendException {
		try {
			return personService.findAll(searchDto);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BackendException(e.getMessage(), e);
		}
	}

	@Override
	public Page<PersonEntity> findAll(SearchDto searchDto, Pageable pageable) throws BackendException {
		try {
			return personService.findAll(searchDto, pageable);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BackendException(e.getMessage(), e);
		}
	}

	@Override
	public PersonEntity getOne(Long id) throws BackendException {
		try {
			return personService.getOne(id);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BackendException(e.getMessage(), e);
		}
	}
}