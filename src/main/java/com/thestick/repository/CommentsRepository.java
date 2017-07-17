package com.thestick.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thestick.domain.Comment;

public interface CommentsRepository extends JpaRepository<Comment, Long>{
	Comment findById(long id);
}
