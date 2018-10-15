package de.homedev.thymeleaf.simplegui.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import de.homedev.thymeleaf.simplegui.dao.PersonDao;
import de.homedev.thymeleaf.simplegui.model.PersonEntity;
import de.homedev.thymeleaf.simplegui.model.SearchDto;

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

    @Override
    @Transactional(readOnly = true)
    public List<PersonEntity> findAll(SearchDto searchDto) {
        String firstName = StringUtils.isEmpty(searchDto.getFirstName()) ? "%" : searchDto.getFirstName();
        String lastName = StringUtils.isEmpty(searchDto.getLastName()) ? "%" : searchDto.getLastName();
        return personDao.findByFirstNameAndLastName(firstName, lastName);
    }

}
