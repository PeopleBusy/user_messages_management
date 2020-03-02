package com.users.messages.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.users.messages.management.entity.Users;

public interface ICustomUserDetailsService extends UserDetailsService {
	UserDetails loadUserByUsername(String username);
	Optional<Users> findByUsername(String username);
	List<Users> getAllUsers();
	Optional<Users> getUserById(Long id);	
	Optional<Users> getAuthenticatedUser(Users user);
	Users createOrUpdateUser(Users user);
	void deleteUser(Users user);
}
