package com.users.messages.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.users.messages.management.entity.Message;

public interface IMessagesRepository extends JpaRepository<Message, Long> {
	@Query("select m from Message m where m.createdBy.username = :x")
    public List<Message> getUserMessages(@Param("x") String username);
}
