package de.homedev.thymeleaf.simplegui.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.homedev.thymeleaf.simplegui.form.PersonForm;
import de.homedev.thymeleaf.simplegui.model.Person;

@Controller
public class MainController {

	// Inject via application.properties
	@Value("${welcome.message}")
	private String message;

	@Value("${error.message}")
	private String errorMessage;

	@Resource
	private PersonForm data;

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(Model model) {

		model.addAttribute("message", message);

		return "index";
	}

	@RequestMapping(value = { "/personList" }, method = RequestMethod.GET)
	public String personList(Model model) {

		model.addAttribute("persons", data.getPersons());

		return "personList";
	}

	@RequestMapping(value = { "/newPerson" }, method = RequestMethod.GET)
	public String showNewPersonPage(Model model) {
		model.addAttribute("personForm", data);
		return "addPerson";
	}

	@RequestMapping(value = { "/editPerson" }, method = RequestMethod.GET)
	public String showAddPersonPage(Model model, @RequestParam(required = false) Long selectedPersonId) {
		data.setSelectedPerson(data.findPerson(selectedPersonId));
		model.addAttribute("personForm", data);
		return "addPerson";
	}

	@RequestMapping(value = { "/deletePerson" }, method = RequestMethod.GET)
	public String deletePerson(Model model, @RequestParam(required = false) Long selectedPersonId) {
		data.deletePerson(selectedPersonId);
		model.addAttribute("personForm", data);
		return "redirect:personList";
	}

	@RequestMapping(value = { "/editPerson" }, method = RequestMethod.POST)
	public String savePerson(Model model, //
			@ModelAttribute("personForm") PersonForm personForm) {

		String firstName = personForm.getSelectedPerson().getFirstName();
		String lastName = personForm.getSelectedPerson().getLastName();
		if (firstName != null && firstName.length() > 0 //
				&& lastName != null && lastName.length() > 0) {
			data.savePerson(personForm.getSelectedPerson());
			data.setSelectedPerson(new Person());
			return "redirect:/personList";
		}

		model.addAttribute("errorMessage", errorMessage);
		return "addPerson";
	}

}