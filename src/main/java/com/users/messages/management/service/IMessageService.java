package com.users.messages.management.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.users.messages.management.entity.Message;


public interface IMessageService {
	Page<Message> getAllMessageWithPagination(int page, int size);
	Page<Message> getUserMessagesWithPagination(Long userId, int page, int size);
	Optional<Message> getMessageById(Long id);	
	Message createOrUpdateMessage(Message message);
	void deleteMessage(Message message);
}
