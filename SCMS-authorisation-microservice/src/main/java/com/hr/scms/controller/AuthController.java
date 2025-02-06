package com.hr.scms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hr.scms.dto.LoginUser;
import com.hr.scms.model.RegisterUser;
import com.hr.scms.service.UserRegistrationService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserRegistrationService userRegistrationService;

	@PostMapping("/login")
	public String login(@RequestBody LoginUser loginUser) {
		return userRegistrationService.verifyUser(loginUser);
	}
	
	@PostMapping("/register")
	public RegisterUser registerUser(@RequestBody RegisterUser registerUser) {
		return userRegistrationService.registeringUser(registerUser);
	}

}
