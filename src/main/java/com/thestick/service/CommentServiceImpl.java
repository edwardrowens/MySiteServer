package com.thestick.service;

import static com.google.common.base.Preconditions.checkArgument;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thestick.domain.CommentThread;
import com.thestick.domain.User;
import com.thestick.model.Comment;
import com.thestick.repository.CommentThreadsRepository;
import com.thestick.repository.CommentsRepository;
import com.thestick.repository.UsersRepository;

@Service
public class CommentServiceImpl implements CommentService {
	
	private final CommentsRepository commentRepository;
	private final ModelMapper modelMapper;
	private final CommentThreadsRepository commentThreadsRepository;
	private final UsersRepository usersRepository;

	@Autowired
	public CommentServiceImpl(CommentsRepository commentRepository,
							  UsersRepository usersRepository,
							  CommentThreadsRepository commentThreadsRepository,
							  ModelMapper modelMapper) {
		this.commentRepository = commentRepository;
		this.usersRepository = usersRepository;
		this.commentThreadsRepository = commentThreadsRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public Comment retrieveComment(long commentId) {
		return modelMapper.map(commentRepository.findById(commentId), Comment.class);
	}

	@Override
	@Transactional
	public long createComment(Comment comment) {
		User user = usersRepository.findByUsername(comment.getUsername());
		CommentThread commentThread = commentThreadsRepository.findById(comment.getCommentThreadId());
		checkArgument(user != null, "Invalid username");
		checkArgument(commentThread != null, "Invalid comment thread");
		com.thestick.domain.Comment createdComment = commentRepository.saveAndFlush(new com.thestick.domain.Comment(user, comment.getTimestamp(), comment.getContents(), commentThread));
		return createdComment.getId();
	}
}
