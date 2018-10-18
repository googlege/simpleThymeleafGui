package de.homedev.thymeleaf.simplegui.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.homedev.thymeleaf.simplegui.form.PersonForm;
import de.homedev.thymeleaf.simplegui.model.PersonEntity;
import de.homedev.thymeleaf.simplegui.service.IPersonService;

@Controller
public class MainController {

	// Inject via application.properties
	@Value("${welcome.message}")
	private String message;

	@Value("${error.message}")
	private String errorMessage;

	@Resource
	private PersonForm data;

	@Autowired
	private IPersonService personService;

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(Model model) {

		model.addAttribute("message", message);

		return "index";
	}

	@RequestMapping(value = { "/personList" }, method = RequestMethod.GET)
	public String personList(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		data.getPersonList(personService);
		final int currentPage = page.isPresent() ? page.get() : data.getPageable().getPageNumber() + 1;
		final int pageSize = size.isPresent() ? size.get() : data.getPageable().getPageSize();
		if ((currentPage != data.getPageable().getPageNumber() + 1) || (pageSize != data.getPageable().getPageSize())) {
			data.setPageable(PageRequest.of(currentPage - 1, pageSize));
			data.setPersonList(personService.findAll(data.getSearchDto(), data.getPageable()));
		}
		model.addAttribute("page", currentPage);
		model.addAttribute("size", pageSize);
		if (data.getPersonList().getTotalPages() > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, data.getPersonList().getTotalPages()).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("personForm", data);
		return "personList";
	}

	@RequestMapping(value = { "/filterList" }, method = RequestMethod.POST)
	public String showNewPersonPage(Model model, @ModelAttribute("personForm") PersonForm personForm) {
		int currentPage = 1;
		final int pageSize = data.getPageable().getPageSize();
		data.setSearchDto(personForm.getSearchDto());
		data.setPageable(PageRequest.of(currentPage - 1, pageSize));
		data.setPersonList(personService.findAll(data.getSearchDto(), data.getPageable()));
		return "redirect:personList";
	}

	@RequestMapping(value = { "/editPerson" }, method = RequestMethod.GET)
	public String showAddPersonPage(Model model, @RequestParam(required = false) Long selectedPersonId) {
		PersonEntity selectedPerson = personService.getOne(selectedPersonId);
		data.setSelectedPerson(selectedPerson);
		model.addAttribute("personForm", data);
		return "addPerson";
	}

	@RequestMapping(value = { "/deletePerson" }, method = RequestMethod.GET)
	public String deletePerson(Model model, @RequestParam(required = false) Long selectedPersonId) {
		personService.deleteById(selectedPersonId);
		final int currentPage = 1;
		final int pageSize = data.getPageable().getPageSize();
		data.setPageable(PageRequest.of(currentPage - 1, pageSize));
		data.setPersonList(personService.findAll(data.getSearchDto(), data.getPageable()));

		return "redirect:personList";
	}

	@RequestMapping(value = { "/editPerson" }, method = RequestMethod.POST)
	public String savePerson(Model model, //
			@ModelAttribute("personForm") PersonForm personForm) {
		String firstName = personForm.getSelectedPerson().getFirstName();
		String lastName = personForm.getSelectedPerson().getLastName();
		if (firstName != null && firstName.length() > 0 //
				&& lastName != null && lastName.length() > 0) {
			personService.save(personForm.getSelectedPerson());
			data.setPersonList(personService.findAll(data.getSearchDto(), data.getPageable()));
			return "redirect:/personList";
		}

		model.addAttribute("errorMessage", errorMessage);
		return "addPerson";
	}

}
