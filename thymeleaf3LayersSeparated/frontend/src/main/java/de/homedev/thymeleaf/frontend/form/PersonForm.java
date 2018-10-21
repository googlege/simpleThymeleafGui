package de.homedev.thymeleaf.frontend.form;

import java.rmi.RemoteException;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import de.homedev.thymeleaf.api.fassade.IPersonFassade;
import de.homedev.thymeleaf.api.model.PersonEntity;
import de.homedev.thymeleaf.api.model.SearchDto;

@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class PersonForm {
	private Page<PersonEntity> personList = null;
	private PersonEntity selectedPerson = new PersonEntity();
	private SearchDto searchDto = new SearchDto();
	private Pageable pageable = PageRequest.of(0, 5);

	public Page<PersonEntity> getPersonList(IPersonFassade personService) throws RemoteException {
		if (this.personList == null) {
			this.personList = personService.findAll(pageable);
		}
		return personList;
	}

	public Page<PersonEntity> getPersonList() {
		return personList;
	}

	public void setPersonList(Page<PersonEntity> personList) {
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

	public Pageable getPageable() {
		return pageable;
	}

	public void setPageable(Pageable pageable) {
		this.pageable = pageable;
	}

}
