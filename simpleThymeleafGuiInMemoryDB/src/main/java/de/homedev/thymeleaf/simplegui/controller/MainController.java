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
import de.homedev.thymeleaf.simplegui.model.PersonEntity;

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
		model.addAttribute("person", new PersonEntity());
		return "addPerson";
	}

	@RequestMapping(value = { "/editPerson" }, method = RequestMethod.GET)
	public String showAddPersonPage(Model model, @RequestParam(required = false) Long selectedPersonId) {
		model.addAttribute("person", data.findPerson(selectedPersonId));
		return "addPerson";
	}

	@RequestMapping(value = { "/deletePerson" }, method = RequestMethod.GET)
	public String deletePerson(Model model, @RequestParam(required = false) Long selectedPersonId) {
		data.deletePerson(selectedPersonId);
		model.addAttribute("persons", data.getPersons());
		return "redirect:personList";
	}

	@RequestMapping(value = { "/editPerson" }, method = RequestMethod.POST)
	public String savePerson(Model model, //
			@ModelAttribute("person") PersonEntity selectedPerson) {

		String firstName = selectedPerson.getFirstName();
		String lastName = selectedPerson.getLastName();
		if (firstName != null && firstName.length() > 0 //
				&& lastName != null && lastName.length() > 0) {
			data.savePerson(selectedPerson);
			return "redirect:/personList";
		}

		model.addAttribute("errorMessage", errorMessage);
		return "addPerson";
	}

}