package de.homedev.thymeleaf.simplegui.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.homedev.thymeleaf.simplegui.config.MessageComponent;
import de.homedev.thymeleaf.simplegui.form.PersonForm;
import de.homedev.thymeleaf.simplegui.model.PersonEntity;
import de.homedev.thymeleaf.simplegui.service.api.IPersonService;

@Controller
public class MainController extends AbstractController {

	private final PersonForm data;
	private final IPersonService personService;

	@Autowired
	public MainController(PersonForm data, IPersonService personService, MessageComponent messageComponent) {
		super(messageComponent);
		this.data = data;
		this.personService = personService;
	}

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(Model model) {
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
			final RedirectAttributes redirectAttributes, @ModelAttribute("personForm") PersonForm personForm) {
		String firstName = personForm.getSelectedPerson().getFirstName();
		String lastName = personForm.getSelectedPerson().getLastName();
		if (firstName != null && firstName.length() > 0 //
				&& lastName != null && lastName.length() > 0) {
			personService.save(personForm.getSelectedPerson());
			data.setPersonList(personService.findAll(data.getSearchDto(), data.getPageable()));
			return "redirect:/personList";
		}
		model.addAttribute("errorMessage", getMessageComponent().getMessage("error.message"));
		this.addSuccessMessageAndTranslate("error.message", redirectAttributes);
		return "addPerson";
	}

}
