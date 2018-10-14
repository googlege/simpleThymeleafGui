package de.homedev.thymeleaf.simplegui.form;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import de.homedev.thymeleaf.simplegui.model.Person;

@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class PersonForm {

	private final List<Person> persons = new ArrayList<>();
	private Person selectedPerson = new Person();

	private long nextId = 2;

	public PersonForm() {
		persons.add(new Person(0l, "Bill", "Gates"));
		persons.add(new Person(1l, "Steve", "Jobs"));
	}

	public List<Person> getPersons() {
		return persons;
	}

	public Person getSelectedPerson() {
		return selectedPerson;
	}

	public void setSelectedPerson(Person selectedPerson) {
		this.selectedPerson = selectedPerson;
	}

	public Person savePerson(Person person) {
		if (person.getId() == null) {
			person.setId(nextId++);
			getPersons().add(person);
			return person;
		} else {
			Person p = findPerson(person.getId());
			p.setFirstName(person.getFirstName());
			p.setLastName(person.getLastName());
		}
		return null;
	}

	public Person deletePerson(Long id) {
		Iterator<Person> it = persons.iterator();
		while (it.hasNext()) {
			Person p = it.next();
			if (id.equals(p.getId())) {
				it.remove();
				return p;
			}
		}
		return null;
	}

	public Person findPerson(Long id) {
		for (Person p : persons) {
			if (id.equals(p.getId())) {
				return p;
			}
		}
		return null;
	}

}