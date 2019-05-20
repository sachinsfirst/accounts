package com.trial.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trial.dao.UserDao;
import com.trial.model.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	public List<User> listUsers() {
		LOGGER.info("UserService : listUsers() called");
		List<User> users = new ArrayList<>();
		userDao.findAll().forEach(users::add);

		return users;
	}
	
	
    public User findByUsername(String username) {
    	LOGGER.info("UserService : findByUsername() called");
        return userDao.findByUsername(username);
    }

	public User getUserById(int id) {
		LOGGER.info("UserService : getUserById() called");
		User user = userDao.findById(id);
		return user;
	}
	
	public String saveUser(User user) {
		String result = null;
		try {
			userDao.save(user);
			LOGGER.info("UserService : saveUser() - User Saved/Updated");
			result = "User Saved/Updated";
		} catch (Exception e) {
			LOGGER.error("Error while adding User ",e);
			result = "Error while adding User";
		}
		return result;
	}
	
	public String deleteUser(int id) {
		LOGGER.info("UserService : deleteUser() called");
		String result = null;
		
		try {
			User user = getUserById(id);
			if (null != user) {
				userDao.delete(user);
				LOGGER.info("User deleted Successfully!");
				result = "User deleted Successfully";
			} else {
				LOGGER.info("User not found for the given Id : "+id);
				result = "User not found for the given Id";
			}
		} catch (Exception e) {
			LOGGER.error("Error while deleting User id : "+id, e);
			result = "User not found for the given Id";
		}
		return result;
	}
	
	public boolean authenticate(User user, String password) {
    	LOGGER.info("UserService : authenticate() called");
            	    	
    	if(user.getPassword().equalsIgnoreCase(password)) {
    		
    		if(user.isDisabled()) {
    			return false;
    		} else {
    			return true;
    		}    		
    	} else {
    		return false;
    	}
    	
    }
	
}
