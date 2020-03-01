package com.users.messages.management.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.users.messages.management.entity.Message;

public interface IMessagesRepository extends JpaRepository<Message, Long> {
	@Query("select m from Message m where m.createdBy.id = :x")
    public Page<Message> getUserMessagesWithPagination(@Param("x") Long userId, Pageable pageable);
}
