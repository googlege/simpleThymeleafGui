package de.homedev.thymeleaf.simplegui.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.homedev.thymeleaf.simplegui.model.UserEntity;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface UserDao extends BaseMandantDao<UserEntity, Long> {
    @Query("SELECT p FROM UserEntity as p WHERE p.username = ?#{[0]} and p.password = ?#{[1]}")
    public List<UserEntity> findByUsernameAndPassword(String username, String userPassword);

    @Query("SELECT p FROM UserEntity as p WHERE p.username = ?#{[0]}")
    public List<UserEntity> findByUsername(String username);
}
