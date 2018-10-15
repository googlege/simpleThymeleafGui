package de.homedev.thymeleaf.simplegui.form;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import de.homedev.thymeleaf.simplegui.model.PersonEntity;
import de.homedev.thymeleaf.simplegui.model.SearchDto;
import de.homedev.thymeleaf.simplegui.service.IPersonService;

@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class PersonForm {
    private List<PersonEntity> personList = null;
    private PersonEntity selectedPerson = new PersonEntity();
    private SearchDto searchDto = new SearchDto();

    public List<PersonEntity> getPersonList(IPersonService personService) {
        if (this.personList == null) {
            this.personList = personService.findAll();
        }
        return personList;
    }

    public List<PersonEntity> getPersonList() {
        return personList;
    }

    public void setPersonList(List<PersonEntity> personList) {
        this.personList = personList;
    }

    public PersonEntity getSelectedPerson() {
        return selectedPerson;
    }

    public void setSelectedPerson(PersonEntity selectedPerson) {
        this.selectedPerson = selectedPerson;
    }

    public SearchDto getSearchDto() {
        return searchDto;
    }

    public void setSearchDto(SearchDto searchDto) {
        this.searchDto = searchDto;
    }

    //	public PersonEntity savePerson(PersonEntity person) {
    //		PersonEntity result = personService.save(person);
    //		persons.clear();
    //		persons.addAll(personService.findAll());
    //		return result;
    //	}
    //
    //	public void deletePerson(Long id) {
    //		personService.deleteById(id);
    //		persons.clear();
    //		persons.addAll(personService.findAll());
    //	}
    //
    //	public PersonEntity findPerson(Long id) {
    //		return personService.getOne(id);
    //	}

}
