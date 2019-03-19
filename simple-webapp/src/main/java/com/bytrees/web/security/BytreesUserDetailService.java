package com.bytrees.web.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bytrees.service.entity.Users;
import com.bytrees.service.repository.UsersRepository;

public class BytreesUserDetailService implements UserDetailsService  {
	@Autowired
	private UsersRepository userRepository;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Users> users = userRepository.findByName(username);
		if (!users.isPresent()) {
			throw new UsernameNotFoundException("user not exists.");
		}
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();       
		authList.add(new SimpleGrantedAuthority("ROLE_USER"));         
		UserDetails userDetail = new User(users.get().getName(), users.get().getPassword(), authList);
		return userDetail;
	}
}
