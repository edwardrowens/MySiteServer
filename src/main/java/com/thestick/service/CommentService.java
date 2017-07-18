package com.thestick.service;

import com.thestick.model.Comment;

public interface CommentService {
	Comment retrieveComment(long commentId);
	/**
	 * 
	 * @param comment
	 * @return
	 * @throws IllegalArgumentException if the comments username or comment thread Id
	 * 									are invalid
	 */
	long createComment(Comment comment);
}
