package com.hr.scms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hr.scms.dao.UserInfoRepository;
import com.hr.scms.dto.LoginUser;
import com.hr.scms.jwt.JwtUtil;
import com.hr.scms.model.RegisterUser;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserRegistrationService {
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtil JwtUtil;
	
	@Transactional
	public RegisterUser registeringUser(RegisterUser registerUser) {
		registerUser.setPassword(passwordEncoder.encode(registerUser.getPassword()));
		return userInfoRepository.save(registerUser);
	}
	
	
	public String verifyUser(LoginUser users) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(), users.getPassword()));

		if (authentication.isAuthenticated()) {
			log.info("User has been authenticated and JWT token will be generated in next stage");
			//return "Successfully Logged In";
			return JwtUtil.generateToken(users.getUsername());
		}
		return "Authentication Failed";
	}

}
