package com.devRupam.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devRupam.todo.dto.PersonDTO;
import com.devRupam.todo.entity.Person;
import com.devRupam.todo.security.AppResponse;
import com.devRupam.todo.service.PersonServices;
import com.devRupam.todo.service.TodoServices;
import com.jayway.jsonpath.internal.Path;

@RestController
@CrossOrigin(origins="http://192.168.43.114:4200")
public class PersonController {

	@Autowired
	private PersonServices personService;
	
	@Autowired
	private TodoServices todoServices;
	
	@RequestMapping(path="/register", method =RequestMethod.POST)
	public AppResponse userRegistration(@RequestBody Person person)
	{
		return personService.registerUser(person);
	}
	
	@RequestMapping(path="/authenticate", method =RequestMethod.GET)
	public PersonDTO login(ModelMap model)
	{
		String name=null;
		synchronized (this) {
		 User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 name = user.getUsername(); //get logged in username
     	 model.addAttribute("username", name);
		 todoServices.currentUserNameSimple(name);
		 System.out.println("username=" +name);
	}
		
		return personService.gettAllPersonDetails(name);
	}
	
	
	@RequestMapping(path="/getAll", method=RequestMethod.GET)
	public List<Person> getAllPersonDetails()
	{
		return personService.getAllPerson();
	}
	
	
}
