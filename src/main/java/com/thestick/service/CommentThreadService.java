package com.thestick.service;

import com.thestick.model.CommentThread;

public interface CommentThreadService {
	long createCommentThread(CommentThread commentThread);
	CommentThread retrieveCommentThread(long commentThreadId);
}
