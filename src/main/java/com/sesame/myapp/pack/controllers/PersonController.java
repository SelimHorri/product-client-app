package com.sesame.myapp.pack.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sesame.myapp.pack.forms.PersonForm;
import com.sesame.myapp.pack.models.Person;

@Controller
public class PersonController {
// Begin Class
	
	private static List<Person> persons = new ArrayList<Person>();
	
	public PersonController() {
		persons.add(new Person("Salim", "Horri"));
		persons.add(new Person("Aziz", "Horri"));
	}
	
	static {
		persons.add(new Person("Abd", "Raouf"));
		persons.add(new Person("Rabii", "Mater"));
	}
	
	// Inject via application.properties
	@Value("${welcome.message}")
	private String message;
	
	@Value("${error.message}")
	private String errorMessage;
	
	@RequestMapping(value = { "/indexPerson", "/Person" }, method = RequestMethod.GET)
	public String index(Model model) {
		
		model.addAttribute("message", message);
		return "pages/index";
	}
	
	@RequestMapping(value = { "/personList" }, method = RequestMethod.GET)
	public String personList(Model model) {
		
		model.addAttribute("persons", persons);
		return "pages/personList";
	}
	
	@RequestMapping(value = { "/addPerson" }, method = RequestMethod.GET)
	public String showAddPersonPage(Model model) {
		
		PersonForm personForm = new PersonForm();
		model.addAttribute("personForm", personForm);
		return "pages/addPerson";
	}

	@RequestMapping(value = { "/addPerson" }, method = RequestMethod.POST)
	public String savePerson(Model model, //
			@ModelAttribute("personForm") PersonForm personForm) {
		
		String firstName = personForm.getFirstName();
		String lastName = personForm.getLastName();
		if (firstName != null && firstName.length() > 0 //
				&& lastName != null && lastName.length() > 0) {
			Person newPerson = new Person(firstName, lastName);
			persons.add(newPerson);
			return "redirect: /personList";
		}
		model.addAttribute("errorMessage", errorMessage);
		return "pages/addPerson";
	}

// End Class
}
