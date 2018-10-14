package de.homedev.thymeleaf.simplegui.form;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import de.homedev.thymeleaf.simplegui.model.PersonEntity;
import de.homedev.thymeleaf.simplegui.service.IPersonService;

@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class PersonForm {

	private final IPersonService personService;
	private final List<PersonEntity> persons;

	@Autowired
	public PersonForm(IPersonService personService) {
		this.personService = personService;
		this.persons = personService.findAll();
	}

	public List<PersonEntity> getPersons() {
		return persons;
	}

	public PersonEntity savePerson(PersonEntity person) {
		PersonEntity result = personService.save(person);
		persons.clear();
		persons.addAll(personService.findAll());
		return result;
	}

	public void deletePerson(Long id) {
		personService.deleteById(id);
		persons.clear();
		persons.addAll(personService.findAll());
	}

	public PersonEntity findPerson(Long id) {
		return personService.getOne(id);
	}

}