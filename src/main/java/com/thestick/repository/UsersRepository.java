package com.thestick.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thestick.domain.User;

public interface UsersRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
