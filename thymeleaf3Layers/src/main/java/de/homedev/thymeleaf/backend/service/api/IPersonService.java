package de.homedev.thymeleaf.backend.service.api;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import de.homedev.thymeleaf.api.model.PersonEntity;
import de.homedev.thymeleaf.api.model.SearchDto;

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
