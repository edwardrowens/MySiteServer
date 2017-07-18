package com.thestick.service;

import static com.google.common.base.Preconditions.checkArgument;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thestick.domain.User;
import com.thestick.model.CommentThread;
import com.thestick.repository.CommentThreadsRepository;
import com.thestick.repository.UsersRepository;

@Service
public class CommentThreadServiceImpl implements CommentThreadService {
	
	private final CommentThreadsRepository commentThreadsRepository;
	private final UsersRepository usersRepository;

	@Autowired
	public CommentThreadServiceImpl(CommentThreadsRepository commentThreadsRepository,
									UsersRepository usersRepository) {
		this.commentThreadsRepository = commentThreadsRepository;
		this.usersRepository = usersRepository;
	}

	@Override
	public long createCommentThread(CommentThread commentThread) {
		User user = usersRepository.findByUsername(commentThread.getCreator());
		checkArgument(user != null, "Invalid username");
		com.thestick.domain.CommentThread commentThreadDomain = commentThreadsRepository.save(new com.thestick.domain.CommentThread(commentThread.getCreated(), commentThread.getCreator()));
		return commentThreadDomain.getId();
	}

	@Override
	public CommentThread retrieveCommentThread(long commentThreadId) {
		com.thestick.domain.CommentThread commentThread = commentThreadsRepository.findById(commentThreadId);
		checkArgument(commentThread != null, "Invalid comment thread id");
		return CommentThread.create(commentThread.getId(), commentThread.getCreated(), commentThread.getCreator());
	}
}
