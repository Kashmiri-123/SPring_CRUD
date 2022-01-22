package com.kashmiri.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kashmiri.todo.model.User;
import com.kashmiri.todo.services.UserService;

@RestController
@RequestMapping("api/user")
public class UserController {

	@Autowired
	private UserService service;
	
	@PostMapping("/register")
	public ResponseEntity<User> user(@RequestBody User user) {
		return new ResponseEntity<User> (service.register(user), HttpStatus.OK);
	}
	
	@GetMapping("")
	public ResponseEntity<List<User>> users() {
		return new ResponseEntity<List<User>> (service.getAllUsers(), HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") long userId) {
		return new ResponseEntity<User> (service.updateUser(user, userId), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> removeUser(@PathVariable("id") long userId) {
		if(service.removeUser(userId)) {
			return new ResponseEntity<String> ("Deleted successfully" ,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String> ("Something went wrong", HttpStatus.NOT_FOUND);
		}
	}
}
