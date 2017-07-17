package com.thestick.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thestick.model.Comment;
import com.thestick.repository.CommentsRepository;

@Service
public class CommentServiceImpl implements CommentService {
	
	private final CommentsRepository commentRepository;
	private final ModelMapper modelMapper;

	@Autowired
	public CommentServiceImpl(CommentsRepository commentRepository,
							  ModelMapper modelMapper) {
		this.commentRepository = commentRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public Comment retrieveComment(long commentId) {
		return modelMapper.map(commentRepository.findById(commentId), Comment.class);
	}

	@Override
	@Transactional
	public long createComment(Comment comment) {
		com.thestick.domain.Comment createdComment = commentRepository.saveAndFlush(new com.thestick.domain.Comment(comment.username(), comment.timestamp(), comment.contents(), comment.threadId()));
		return createdComment.getId();
	}
}
