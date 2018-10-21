package de.homedev.thymeleaf.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.homedev.thymeleaf.api.model.UserRoleEntity;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface UserRoleDao extends JpaRepository<UserRoleEntity, Long> {
    @Query("SELECT p FROM UserRoleEntity as p WHERE p.authority = ?#{[0]}")
    public List<UserRoleEntity> findByAuthorityName(String authority);
}
