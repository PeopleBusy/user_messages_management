package com.users.messages.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.users.messages.management.entity.Message;
import com.users.messages.management.repository.IMessagesRepository;

@Service
public class MessageService implements IMessageService {
	
	@Autowired
	private IMessagesRepository messageRepository;

	@Override
	public List<Message> getAllMessages() {
		return messageRepository.findAll();
	}
	
	@Override
	public List<Message> getUserMessages(String username) {
		return messageRepository.getUserMessages(username);
	}

	@Override
	public Optional<Message> getMessageById(Long id) {
		return messageRepository.findById(id);
	}

	@Override
	public Message createOrUpdateMessage(Message message) {
		return messageRepository.saveAndFlush(message);
	}

	@Override
	public void deleteMessage(Long id) {
		messageRepository.deleteById(id);
	}
}
