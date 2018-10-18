package de.homedev.thymeleaf.simplegui.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import de.homedev.thymeleaf.simplegui.model.PersonEntity;
import de.homedev.thymeleaf.simplegui.model.SearchDto;

public interface IPersonService {
	public static final String SERVICE_NAME = "personServiceImpl";

	public PersonEntity save(PersonEntity person);

	public void deleteById(Long pk);

	public List<PersonEntity> findAll();

	public Page<PersonEntity> findAll(Pageable pageable);

	public List<PersonEntity> findAll(SearchDto searchDto);

	public Page<PersonEntity> findAll(SearchDto searchDto, Pageable pageable);

	public PersonEntity getOne(Long id);
}
