package com.users.messages.management.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.users.messages.management.entity.Message;
import com.users.messages.management.entity.Users;
import com.users.messages.management.helper.AuthenticationHelper;
import com.users.messages.management.service.ICustomUserDetailsService;
import com.users.messages.management.service.IMessageService;

@Controller
public class MessageController {
	
	@Autowired
	private IMessageService msgService;
	
	@Autowired
	private ICustomUserDetailsService usersService;
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/secured/list-messages", method = RequestMethod.GET)
	public String ListMessages(ModelMap model, HttpServletRequest request) {
	    model.addAttribute("title", "All messages");
		model.addAttribute("localDateTimeFormat", DateTimeFormatter.ofPattern("MM/dd/yyy HH:mm"));
		model.put("messages", msgService.getAllMessages());
		return "secured/list-messages";
	}
	
	@RequestMapping(value = "/secured/my-messages", method = RequestMethod.GET)
	public String MyMessages(ModelMap model) {
		model.addAttribute("title", "My messages");
		model.addAttribute("localDateTimeFormat", DateTimeFormatter.ofPattern("MM/dd/yyy HH:mm"));
		model.put("messages", msgService.getUserMessages(AuthenticationHelper.getAuthenticatedUsername()));
		return "secured/list-messages";
	}
	
	@RequestMapping(value = "/secured/create-message", method = RequestMethod.GET)
	public String createMessage(ModelMap model) {
		model.addAttribute("title", "Create message");
		model.addAttribute("message", new Message());
		return "secured/create-message";
	}
	
	@RequestMapping(value = "/secured/create-message", method = RequestMethod.POST)
	public String createMessage(ModelMap model, @Valid @ModelAttribute("message") Message message, BindingResult result) {

		Users user = usersService.findByUsername(AuthenticationHelper.getAuthenticatedUsername()).get();
		message.setCreatedBy(user);
		message.setCreatedTs(LocalDateTime.now());
        
		msgService.createOrUpdateMessage(message);			
		model.addAttribute("message", new Message());
		model.addAttribute("msg", "Creation succeeded!");
		
		return "secured/create-message"; 
	}
	
	@RequestMapping(value = "/secured/update-message", method = RequestMethod.GET) 
	public String updateMessage(@RequestParam long id, ModelMap model) {
		model.addAttribute("title", "Update message");
		model.put("message", msgService.getMessageById(id));
		return "secured/create-message";
	}
	
	@RequestMapping(value = "/secured/update-message", method = RequestMethod.POST)
	public String updateMessage(ModelMap model, @Valid @ModelAttribute("message") Message message, BindingResult result) {
		
		Users user = usersService.findByUsername(AuthenticationHelper.getAuthenticatedUsername()).get();
		
		Message dbMsg = msgService.getMessageById(message.getId()).get();
		
		dbMsg.setModifiedBy(user);
		dbMsg.setModifiedTs(LocalDateTime.now());
		dbMsg.setTextMessage(message.getTextMessage());
        
		msgService.createOrUpdateMessage(dbMsg);
		
		model.addAttribute("message", new Message());
		model.addAttribute("msg", "Updated successfully!");
		 
		return "secured/create-message";
	}
	
	@RequestMapping(value = "/secured/show-message", method = RequestMethod.GET)
	public String showMessage(@RequestParam long id, ModelMap model) {
		model.addAttribute("title", "Message " + id + " details");
		model.addAttribute("message", msgService.getMessageById(id).get());
		return "secured/show-message";
	}
	
	@RequestMapping(value = "/secured/delete-message", method = RequestMethod.GET)
	public String deleteMessage(@RequestParam long id, ModelMap model) {
		msgService.deleteMessage(id);
		
		return "redirect:my-messages";
	}
}
