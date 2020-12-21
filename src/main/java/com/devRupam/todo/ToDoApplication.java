package com.devRupam.todo;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ToDoApplication implements CommandLineRunner {


	
	public static void main(String[] args) {
		SpringApplication.run(ToDoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
     
		/*Person p1 = new Person();
		p1.setUsername("abc");
		p1.setPassword(getPasswordEncoders().encode("xyz"));
		p1.setRoles("ROLE_USER");
		
		Person p2 = new Person();
		p2.setUsername("hello");
		p2.setPassword(getPasswordEncoders().encode("xyz"));
		p2.setRoles("ROLE_ADMIN");
		personService.save(p1);
		personService.save(p2);*/
		
	}
}
