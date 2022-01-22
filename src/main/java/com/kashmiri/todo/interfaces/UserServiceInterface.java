package com.kashmiri.todo.interfaces;

import java.util.List;

import com.kashmiri.todo.model.User;

public interface UserServiceInterface {
	User register(User user);
	
	List<User> getAllUsers();
	
	User getUserById(long userId);
	
	User updateUser(User user, long userId); 
	
	boolean removeUser(long userId); 

}
