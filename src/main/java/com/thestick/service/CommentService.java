package com.thestick.service;

import com.thestick.model.Comment;

public interface CommentService {
	Comment retrieveComment(long commentId);
	long createComment(Comment comment);
}
