package com.users.messages.management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.users.messages.management.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
	Optional<Users> findByUsername(String username);
}
