package com.users.messages.management.service;

import java.util.List;
import java.util.Optional;

import com.users.messages.management.entity.Message;


public interface IMessageService {
	List<Message> getAllMessages();
	List<Message> getUserMessages(String username);
	Optional<Message> getMessageById(Long id);	
	Message createOrUpdateMessage(Message message);
	void deleteMessage(Long id);
}
