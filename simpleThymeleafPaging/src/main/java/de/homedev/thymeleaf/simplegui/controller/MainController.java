package de.homedev.thymeleaf.simplegui.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = {
            "/", "/index"
    }, method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("message", message);

        return "index";
    }

    @RequestMapping(value = {
            "/personList"
    }, method = RequestMethod.GET)
    public String personList(Model model) {
        data.getPersonList(personService);
        model.addAttribute("personForm", data);

        return "personList";
    }

    @RequestMapping(value = {
            "/filterList"
    }, method = RequestMethod.POST)
    public String showNewPersonPage(Model model, @ModelAttribute("personForm") PersonForm personForm) {
        data.setSearchDto(personForm.getSearchDto());
        data.setPersonList(personService.findAll(personForm.getSearchDto()));
        model.addAttribute("personForm", data);
        return "personList";
    }

    @RequestMapping(value = {
            "/editPerson"
    }, method = RequestMethod.GET)
    public String showAddPersonPage(Model model, @RequestParam(required = false) Long selectedPersonId) {
        PersonEntity selectedPerson = personService.getOne(selectedPersonId);
        data.setSelectedPerson(selectedPerson);
        model.addAttribute("personForm", data);
        return "addPerson";
    }

    @RequestMapping(value = {
            "/deletePerson"
    }, method = RequestMethod.GET)
    public String deletePerson(Model model, @RequestParam(required = false) Long selectedPersonId) {
        personService.deleteById(selectedPersonId);
        data.setPersonList(personService.findAll(data.getSearchDto()));
        model.addAttribute("personForm", data);
        return "redirect:personList";
    }

    @RequestMapping(value = {
            "/editPerson"
    }, method = RequestMethod.POST)
    public String savePerson(Model model, //
                             @ModelAttribute("personForm") PersonForm personForm) {
        String firstName = personForm.getSelectedPerson().getFirstName();
        String lastName = personForm.getSelectedPerson().getLastName();
        if (firstName != null && firstName.length() > 0 //
            && lastName != null && lastName.length() > 0) {
            personService.save(personForm.getSelectedPerson());
            data.setPersonList(personService.findAll(data.getSearchDto()));
            return "redirect:/personList";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "addPerson";
    }

}
