package com.kashmiri.todo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kashmiri.todo.interfaces.UserServiceInterface;
import com.kashmiri.todo.model.User;
import com.kashmiri.todo.repository.UserRepository;

@Service
public class UserService implements UserServiceInterface{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User register(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(long userId) {
		Optional<User> user = userRepository.findById(userId);
		
		if(user.isPresent()) {
			return user.get();
		}
		else {
			return null;
		}
	}

	@Override
	public User updateUser(User user, long userId) {
		User existingUser = getUserById(userId);
		
		if(existingUser != null) {
			existingUser.setName(user.getName());
			existingUser.setEmail(user.getEmail());
			existingUser.setPassword(user.getPassword());
			existingUser.setPhoneNumber(user.getPhoneNumber());
			
			userRepository.save(existingUser);
			return existingUser;
		}
		else {
			return null;
		}
	}

	@Override
	public boolean removeUser(long userId) {
		try {
			User existingUser = getUserById(userId);
			userRepository.delete(existingUser);
			return true;
		} 
		catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			return false;
		}
	}

}
