package de.homedev.thymeleaf.simplegui.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.homedev.thymeleaf.simplegui.dao.UserDao;
import de.homedev.thymeleaf.simplegui.dao.UserRoleDao;
import de.homedev.thymeleaf.simplegui.model.UserEntity;
import de.homedev.thymeleaf.simplegui.model.UserRoleEntity;
import de.homedev.thymeleaf.simplegui.service.api.IUserService;

@Service(IUserService.SERVICE_NAME)
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class UserServiceImpl implements IUserService, UserDetailsService {
    private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public List<UserEntity> findUser(String username, String userPassword) {
        return userDao.findByUsernameAndPassword(username, userPassword);
    }

    @Override
    public UserEntity saveUser(UserEntity entity) {
        return userDao.save(entity);
    }

    @Override
    public UserRoleEntity saveAuthority(UserRoleEntity entity) {
        return userRoleDao.save(entity);
    }

    @Override
    public void withdrawAuthorityByUser(UserEntity userEntity, UserRoleEntity authorityEntity) {
        userEntity = userDao.getOne(userEntity.getId());
        authorityEntity = userRoleDao.getOne(authorityEntity.getId());
        userEntity.setRole(authorityEntity);
        userDao.save(userEntity);

    }

    @Override
    public List<UserRoleEntity> findByAuthorityName(String authorityName) {
        return userRoleDao.findByAuthorityName(authorityName);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserEntity> list = userDao.findByUsername(username);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

}
