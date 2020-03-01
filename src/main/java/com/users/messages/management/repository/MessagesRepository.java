package com.users.messages.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.users.messages.management.entity.Message;

public interface MessagesRepository extends JpaRepository<Message, Long> {
	
}
