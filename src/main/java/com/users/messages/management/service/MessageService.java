package com.users.messages.management.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.users.messages.management.entity.Message;
import com.users.messages.management.repository.IMessagesRepository;

@Service
public class MessageService implements IMessageService {
	
	@Autowired
	private IMessagesRepository messageRepository;

	@Override
	public Page<Message> getAllMessageWithPagination(int page, int size) {
		return messageRepository.findAll(PageRequest.of(page, size, Sort.by("id")));
	}
	
	@Override
	public Page<Message> getUserMessagesWithPagination(Long userId, int page, int size) {
		return messageRepository.getUserMessagesWithPagination(userId, PageRequest.of(page, size, Sort.by("id")));
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
	public void deleteMessage(Message message) {
		messageRepository.delete(message);
	}
}
