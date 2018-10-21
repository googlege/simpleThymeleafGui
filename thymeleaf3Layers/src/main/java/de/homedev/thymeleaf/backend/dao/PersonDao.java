package de.homedev.thymeleaf.backend.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.homedev.thymeleaf.api.model.PersonEntity;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface PersonDao extends JpaRepository<PersonEntity, Long> {

	@Query("SELECT p FROM PersonEntity p WHERE p.firstName LIKE :firstName AND p.lastName LIKE :lastName")
	public List<PersonEntity> findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

	@Query("SELECT p FROM PersonEntity p WHERE p.firstName LIKE :firstName AND p.lastName LIKE :lastName")
	public Page<PersonEntity> findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName, Pageable pageable);
}
