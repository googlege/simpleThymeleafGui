package de.homedev.thymeleaf.simplegui.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.homedev.thymeleaf.simplegui.dao.PersonDao;
import de.homedev.thymeleaf.simplegui.model.PersonEntity;

@Service(IPersonService.SERVICE_NAME)
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class PersonServiceImpl implements IPersonService {
	// private static Logger log =
	// LoggerFactory.getLogger(PersonServiceImpl.class);

	@Autowired
	private PersonDao personDao;

	@Override
	public PersonEntity save(PersonEntity entity) {
		return personDao.save(entity);
	}

	@Override
	public void deleteById(Long pk) {
		personDao.deleteById(pk);

	}

	@Transactional(readOnly = true)
	@Override
	public List<PersonEntity> findAll() {
		return personDao.findAll();
	}

	@Override
	public PersonEntity getOne(Long id) {
		return personDao.getOne(id);
	}

}
