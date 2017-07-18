package com.thestick.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thestick.domain.CommentThread;

public interface CommentThreadsRepository extends JpaRepository<CommentThread, Long> {
	CommentThread findById(long id);
}
