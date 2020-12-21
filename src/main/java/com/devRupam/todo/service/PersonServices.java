package com.devRupam.todo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.devRupam.todo.dto.PersonDTO;
import com.devRupam.todo.entity.Person;
import com.devRupam.todo.repository.PersonRepository;
import com.devRupam.todo.security.AppResponse;

@Service
public class PersonServices implements UserDetailsService {

	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByUsername(username);
        ArrayList<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
        SimpleGrantedAuthority role = new SimpleGrantedAuthority(person.getPassword());
        roles.add(role);
        User user = new User(username, person.getPassword(), roles); 
	    return user;
	}
	
	
    public PersonDTO gettAllPersonDetails(String username)
    {
    	PersonDTO p=null;
    	synchronized (username) {
			 p= personRepository.getAllPersonDetails(username);
		}
    	
    	return p;
    }
	
	public AppResponse registerUser(Person person)
	{
		String message;
		String username = person.getUsername();
		String email = person.getEmail();
		System.out.println("email="+email);
		PersonDTO personDetails = personRepository.getPerson(username, email);
		
		if(personDetails!=null)
		{
			message="User is already existing, Please login.";
			return new AppResponse(message);
		}
		person.setPassword(getPasswordEncoder().encode(person.getPassword()));
		person.setRoles("ROLE_USER");
		personRepository.save(person);
		message = "Registered successfully";
		return new AppResponse(message);
	}
	
	public Person save(Person person)
	{
		person.setLast_login(new Date());
		personRepository.save(person);
		return person;
	}
	
	public List<Person> getAllPerson()
	{
		return (List<Person>) personRepository.findAll();
	}
	

	@Bean
	public PasswordEncoder getPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	
	
	
	
	

	
}
