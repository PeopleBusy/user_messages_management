package com.users.messages.management.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.users.messages.management.entity.Role;
import com.users.messages.management.entity.Users;
import com.users.messages.management.helper.AuthenticationHelper;
import com.users.messages.management.service.ICustomUserDetailsService;

@Controller
public class UsersController {
	
	@Autowired
	private ICustomUserDetailsService userService;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/secured/list-users", method = RequestMethod.GET)
	public String ListMessages(ModelMap model) {
		model.addAttribute("localDateTimeFormat", DateTimeFormatter.ofPattern("MM/dd/yyy HH:mm"));
		model.put("users", userService.getAllUsers());
		return "secured/list-users";
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
        	model.addAttribute("error", error);
            model.addAttribute("msg", "Your username and password are invalid!");
        }

        if (logout != null) {
        	model.addAttribute("error", null);
            model.addAttribute("msg", "You have been logged out successfully!");
        }

        return "login";
    }
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String createUser(ModelMap model) {
		model.addAttribute("user", new Users());
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String createUser(ModelMap model, @Valid Users user, BindingResult result) {
		
		if(!user.getPassword().equals(user.getConfirmation())) {
			user.setPassword("");
			user.setConfirmation("");
			model.addAttribute("user", user);
			model.addAttribute("errorMsg", "Passwords don't match!");
		}
		else if (!result.hasErrors()) {
			
			if(userService.findByUsername(user.getUsername()).isPresent()) {
				user.setPassword("");
				user.setConfirmation("");
				model.addAttribute("user", user);
				model.addAttribute("errorMsg", "Username {" + user.getUsername() + "} already used!");
			}else {
				
				user.setCreatedTs(LocalDateTime.now());
				user.getRoles().add(new Role("USER")); 
		        user.setPassword(passwordEncoder.encode(user.getPassword()));
		        
				userService.createOrUpdateUser(user);			
				model.addAttribute("user", new Users());
				model.addAttribute("msg", "Registration succeeded!");
			}
			
		}
		
		return "register"; 
	}
	
	@RequestMapping(value = "/secured/user-profile", method = RequestMethod.GET)
	public String userProfile(ModelMap model) {
		model.addAttribute("user", userService.findByUsername(AuthenticationHelper.getAuthenticatedUsername()).get());
		return "/secured/user-profile";
	}
	
	@RequestMapping(value = "/secured/user-profile", method = RequestMethod.POST)
	public String userProfile(ModelMap model, @Valid @ModelAttribute("user") Users user, BindingResult result) {
		System.err.println(user.getUsername());
		Users dbUser = userService.getUserById(user.getId()).get();
		

		dbUser.setUsername(user.getUsername());
        
		userService.createOrUpdateUser(dbUser);
		
		model.addAttribute("user", dbUser);
		model.addAttribute("msg", "Updated successfully!");
		 
		return "/secured/user-profile";
	}
}
