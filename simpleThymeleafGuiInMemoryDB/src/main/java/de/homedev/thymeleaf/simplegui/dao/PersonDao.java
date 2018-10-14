package de.homedev.thymeleaf.simplegui.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.homedev.thymeleaf.simplegui.model.PersonEntity;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface PersonDao extends JpaRepository<PersonEntity, Long> {

}
