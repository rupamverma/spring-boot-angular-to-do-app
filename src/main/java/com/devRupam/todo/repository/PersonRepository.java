
package com.devRupam.todo.repository;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.devRupam.todo.dto.PersonDTO;
import com.devRupam.todo.entity.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {

	
	public Person findByUsername(String username);
	
	@Query("select new com.devRupam.todo.dto.PersonDTO(p.username,p.email) from Person p where p.username= :username or p.email=:email")
	public PersonDTO getPerson(@Param("username") String username, @Param("email") String email);
	
	@Query("select new com.devRupam.todo.dto.PersonDTO(p.username,p.email) from Person p where p.username= :username")
	public PersonDTO getAllPersonDetails(@Param("username") String username);
	
	/*@Query(value="select * from person p inner join todo t on t.id=p.id where p.id=:id", nativeQuery=true)
	public List<PersonDTO> getAllPersonWithTodo(@Param("id") Integer id);*/
	
}
