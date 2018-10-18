package de.homedev.thymeleaf.simplegui.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import de.homedev.thymeleaf.simplegui.model.AbstractMandantEntity;

@NoRepositoryBean
public interface BaseMandantDao<T extends AbstractMandantEntity, K> extends JpaRepository<T, K> {

    List<T> findAllByMandant(AbstractMandantEntity mandant);

    Optional<T> findByMandantAndId(AbstractMandantEntity mandant, K id);

    void deleteByMandantAndId(AbstractMandantEntity mandant, K id);
}
