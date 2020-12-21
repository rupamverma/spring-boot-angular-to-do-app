package com.devRupam.todo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devRupam.todo.entity.Todo;
import com.devRupam.todo.security.AppResponse;
import com.devRupam.todo.service.TodoServices;

@RestController
@CrossOrigin(origins="http://192.168.43.114:4200")
@RequestMapping("/todo")
public class TodoController {
	
	@Autowired
	private TodoServices todoServices;
	
	@RequestMapping(path="/test", method=RequestMethod.GET)
	public String test()
	{
		return todoServices.getUser();
	}
	@PostMapping
	public AppResponse CreateTodo(@RequestBody Todo todo)
	{
		
		return todoServices.createTodoList(todo);
	}
	
	@GetMapping
	public List<Todo> getAllTodoList()
	{
		return todoServices.getTodosList();
	}
	
	@GetMapping(path="/{id}")
	public Todo getTodoList(@PathVariable Integer id)
	{
		return todoServices.getTodo(id);
	}
	
	@PutMapping
	public AppResponse update(@RequestBody Todo todo)
	{
		return todoServices.updateTodo(todo);
	}
	@DeleteMapping(path="/{id}")
	public AppResponse delete(@PathVariable Integer id)
	{
		return todoServices.deleteTodo(id);
	}
	
	
	
	
	
	
	

}
