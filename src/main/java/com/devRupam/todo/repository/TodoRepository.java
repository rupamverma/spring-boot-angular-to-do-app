package com.devRupam.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.devRupam.todo.entity.Todo;

public interface TodoRepository extends CrudRepository<Todo, Integer> {

	@Query(value="select * from todo where person_id=:id" , nativeQuery=true)
	public List<Todo> findAllTodoList(@Param("id") Integer id);
	
	@Modifying
	@Transactional
	@Query(value="delete from todo where id=:id and person_id=:p_id", nativeQuery=true)
	public Integer deleteTodoByIdAndUserName(@Param("id") Integer id, @Param("p_id") Integer p_id);
}
