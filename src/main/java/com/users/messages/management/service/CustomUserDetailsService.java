package com.users.messages.management.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.users.messages.management.entity.Role;
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
		
		Users user = optionalUsers.get();
		
		return new User(user.getUsername(), user.getPassword(), 
                true, true, true, true, getGrantedAuthorities(user));
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(Users user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
         
        for(Role r : user.getRoles()){
            authorities.add(new SimpleGrantedAuthority("ROLE_" + r.getRoleName()));
        }
        return authorities;
    }
	
	@Override
	public Optional<Users> findByUsername(String username) {
		return usersRepository.findByUsername(username);
	}

	@Override
	public List<Users> getAllUsers() {
		return usersRepository.findAll();
	}

	@Override
	public Optional<Users> getUserById(Long id) {
		return usersRepository.findById(id);
	}

	@Override
	public Optional<Users> getAuthenticatedUser(Users user) {
		return usersRepository.findByUsername(AuthenticationHelper.getAuthenticatedUsername());
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
