package com.devRupam.todo.service;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.devRupam.todo.entity.Person;
import com.devRupam.todo.entity.Todo;
import com.devRupam.todo.repository.PersonRepository;
import com.devRupam.todo.repository.TodoRepository;
import com.devRupam.todo.security.AppResponse;

@Service
public class TodoServices {

	@Autowired
    private PersonRepository personRepository;
	
	@Autowired
	private TodoRepository todoRepository;
	//private Person person;
	
	public AppResponse createTodoList( Todo todos)
	{
	 User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 String name = user.getUsername();
	 Person person = personRepository.findByUsername(name);
	 
		synchronized (person) {
			todos.setPerson(person);
			todoRepository.save(todos);
		}		
		return new AppResponse("Todos created successfully");
	}
	
	public List<Todo> getTodosList()
	{
		 User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 String name = user.getUsername();
		 Person person = personRepository.findByUsername(name);
		 return (List<Todo>) todoRepository.findAllTodoList(person.getId());
	}
	 
	public AppResponse updateTodo(Todo todos)
	{
		 User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 String name = user.getUsername();
		 Person person = personRepository.findByUsername(name);
		synchronized (person) {
			todos.setPerson(person);
			todoRepository.save(todos);
		}
		return new AppResponse("Todos updated successfully");
	}

	public Todo getTodo(Integer id)
	{
		return todoRepository.findById(id).get();
	}
	
	public AppResponse deleteTodo(Integer id)
	{
		 User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 String name = user.getUsername();
		 Person person = personRepository.findByUsername(name);
		synchronized (person) {
			todoRepository.deleteTodoByIdAndUserName(id, person.getId());
		}
		return new AppResponse("Your task deleted successfully");
		
	}
	
	 public AppResponse currentUserNameSimple(String username)
	 {
		Person person=null;
		String personDetails;
		synchronized (this) {
			person = personRepository.findByUsername(username);
		    personDetails = person.getId()+" "+person.getUsername()+" "+person.getEmail()+" "+person.getRoles();
			System.out.println(personDetails);
		}		
		return new AppResponse(personDetails);
	 }
	 
	 
	 public String getUser()
	 {
		 Person person=null;
		 return person.getUsername();
	 }
	
	
}
