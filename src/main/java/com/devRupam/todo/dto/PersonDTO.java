package com.devRupam.todo.dto;

import com.devRupam.todo.entity.Todo;

public class PersonDTO {

	private String username;
	private String email;
	
	public PersonDTO(String username, String email) {
		this.username = username;
		this.email = email;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
