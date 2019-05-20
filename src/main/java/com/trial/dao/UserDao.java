package com.trial.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.trial.model.User;

public interface UserDao extends CrudRepository<User, String> {

	User findByUsername(String username);
	
	@Query(value = " from User")
	public List<User> getAllUsers();
	
	public User findById(int id);	
	
}
