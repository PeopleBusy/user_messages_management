package com.users.messages.management.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.users.messages.management.custom.model.CustomUserDetails;
import com.users.messages.management.entity.Users;
import com.users.messages.management.helper.AuthenticationHelper;
import com.users.messages.management.repository.IUsersRepository;

@Service
public class CustomUserDetailsService implements ICustomUserDetailsService {
	
	@Autowired
	private IUsersRepository usersRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Optional<Users> optionalUsers = usersRepository.findByUsername(username);
		optionalUsers.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		return optionalUsers.map(CustomUserDetails::new).get();
	}

	@Override
	public Page<Users> getAllUsersWithPagination(int page, int size) {
		return usersRepository.findAll(PageRequest.of(page, size, Sort.by("username")));
	}

	@Override
	public Optional<Users> getUserById(Long id) {
		return usersRepository.findById(id);
	}

	@Override
	public Optional<Users> getAuthenticatedUser(Users user) {
		Authentication auth = AuthenticationHelper.getAuthentication();
		return usersRepository.findByUsername(auth.getName());
	}

	@Override
	public Users createOrUpdateUser(Users user) {
		return usersRepository.saveAndFlush(user);
	}

	@Override
	public void deleteUser(Users user) {
		usersRepository.delete(user);
	}

}
