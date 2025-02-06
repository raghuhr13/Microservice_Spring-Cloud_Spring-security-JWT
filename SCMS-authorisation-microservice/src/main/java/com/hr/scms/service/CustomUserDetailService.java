package com.hr.scms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hr.scms.dao.UserInfoRepository;
import com.hr.scms.model.RegisterUser;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserInfoRepository userInfoRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<RegisterUser> user = userInfoRepository.findByUsername(username);
        return user.map(u-> User.withUsername(u.getUsername()).password(u.getPassword())
        		.roles("USER").build()).orElseThrow(()-> new UsernameNotFoundException("There is no such kind of user here"));
    }
	

}
