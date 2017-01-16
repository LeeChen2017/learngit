package com.zsoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zsoft.entity.User;
import com.zsoft.service.UserService;

@RestController
@RequestMapping(value = "/frame")
public class DemoController {
	@Autowired
	private UserService userService;
	@RequestMapping(value="/saveUser",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	public String saveUser(@RequestBody User user){
		return userService.add(user);
	}
	@RequestMapping(value = "/saveUser/{account}" ,
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	public String add(@PathVariable String account){
		return userService.addUser(account);
	}
	@RequestMapping(value = "/user/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	public User add(@PathVariable Long id){
		return userService.getOneUser(id);
	}
	 
	
	
	
}